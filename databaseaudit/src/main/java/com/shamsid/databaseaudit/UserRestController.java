package com.shamsid.databaseaudit;

import com.shamsid.databaseaudit.user.User;
import com.shamsid.databaseaudit.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public void saveUser(){
        User user = new User();
        user.setEmail("mail.shamshersiddiqui@gmail.com");
        user.setName("Shamsher Ahmed");
        user.setMobile("9988696746");
        userRepository.save(user);
    }
}
