package com.example.OneToOneChatApp.controller;

import com.example.OneToOneChatApp.model.ChatMessage;
import com.example.OneToOneChatApp.service.ChatMessageService;
import com.example.OneToOneChatApp.util.ChatNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){

        ChatMessage saveMessage =  chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(

                chatMessage.getRecipientId(),"queue/messages", ChatNotification.builder()
                        .id(saveMessage.getId())
                        .senderId(saveMessage.getSenderId())
                        .recipientId(saveMessage.getRecipientId())
                        .content(saveMessage.getContent())
                        .build()
        );
    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
           @PathVariable("senderId") String senderId,
           @PathVariable("recipientId") String recipientId
    ){
            return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }

}
