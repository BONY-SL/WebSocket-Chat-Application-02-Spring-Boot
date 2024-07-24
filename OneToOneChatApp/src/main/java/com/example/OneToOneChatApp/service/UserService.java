package com.example.OneToOneChatApp.service;

import com.example.OneToOneChatApp.model.Status;
import com.example.OneToOneChatApp.model.User;
import com.example.OneToOneChatApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){

        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disConnect(User user){

        var storedUser = userRepository.findById(user.getNickName())
                .orElse(null);

        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }

    }

    public List<User> findConnectedUsers(){


        return userRepository.findAllByStatus(Status.ONLINE);

    }
}
