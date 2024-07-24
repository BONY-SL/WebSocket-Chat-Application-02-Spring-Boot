package com.example.OneToOneChatApp.repository;


import com.example.OneToOneChatApp.model.Status;
import com.example.OneToOneChatApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    List<User> findAllByStatus(Status status);
}
