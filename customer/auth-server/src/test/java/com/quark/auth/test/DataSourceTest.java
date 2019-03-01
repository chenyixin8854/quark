package com.quark.auth.test;

import com.quark.auth.AuthApplication;
import com.quark.auth.entity.User;
import com.quark.auth.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@ActiveProfiles("dev")
public class DataSourceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUser(){
        User user=userService.getUserById(1);
        System.out.println(user);
    }
}
