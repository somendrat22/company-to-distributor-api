package com.supplynext.company_api.jobs;

import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repositories.UserRepository;
import com.supplynext.company_api.services.RoleService;
import com.supplynext.company_api.utilities.CommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
public class UserScheduler {

    @Value("${bot-user-password}")
    String botPassword;
    @Value("${bot-user-email}")
    String botEmail;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Scheduled(initialDelay = 5000, fixedDelay = Long.MAX_VALUE)
    public void insertBotUser(){
        createBotUserIfNotExists();
    }

    public void createBotUserIfNotExists() {


        if (userRepository.findByEmail(botEmail) != null) {
            log.info("BOT user already exists");
            return;
        }

        Role botRole = roleService.createBotRole();
        User botUser = new User();
        botUser.setFullName("System BOT");
        botUser.setEmail(botEmail);
        botUser.setPassword(CommonUtility.encode(botPassword));
        botUser.setPhoneNumber("0000000000");
        botUser.setAddressLine1("SYSTEM");
        botUser.setPincode(000000);
        botUser.setRoles(List.of(botRole));
        botUser.setCreatedAt(LocalDateTime.now());
        botUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(botUser);
        log.info("BOT user created successfully");
    }
}
