package com.spring.spring.services;

import com.spring.spring.models.Message;
import com.spring.spring.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    // //// CREATE NEW MESSAGE ////
    public Message createNewMessage(Message message) {
        return messageRepository.save(message);
    }
}
