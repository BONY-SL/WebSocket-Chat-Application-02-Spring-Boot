package com.example.OneToOneChatApp.repository;

import com.example.OneToOneChatApp.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom,String > {


    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId,String recipientId);
}
