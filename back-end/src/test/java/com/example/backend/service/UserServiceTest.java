package com.example.backend.service;

import java.util.Date;

import com.example.backend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("test1");
        user.setUserAccount("123");
        user.setAvatarUrl("https://th.bing.com/th/id/OIP.wpCgjzigc8llfVXalEJDYAHaEK?rs=1&pid=ImgDetMain");
        user.setGender(0);
        user.setUserPassword("766230");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "user1";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(result);
        Assertions.assertTrue(result > 0);
    }
}