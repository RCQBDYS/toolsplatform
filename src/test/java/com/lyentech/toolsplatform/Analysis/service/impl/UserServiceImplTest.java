package com.lyentech.toolsplatform.Analysis.service.impl;

import com.lyentech.toolsplatform.Analysis.entity.User;
import com.lyentech.toolsplatform.Analysis.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    IUserService iUserService;
    @Test
    public void selectTest(){
      /*  User user = iUserService.(672025);
        System.out.println(user.toString());*/
    }

}