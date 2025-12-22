package com.supplynext.company_api.services;

import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${bot-user-email}")
    String botEmail;
    @Autowired
    UserRepository userRepository;

    public User getBotUser(){
        return userRepository.findByEmail(botEmail);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
