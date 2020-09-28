package com.lyentech.toolsplatform.utils.JWT;

import com.lyentech.toolsplatform.Analysis.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtUtilsTest {
    @Test
    public void getToken(){
        User user = new User();
        user.setUserAccount("672025");
        user.setUserName("王申");
        user.setPassWord("672025");
        user.setUserState(0);
        user.setUserType(0);
        user.setUserUnit("联运质量部");
        System.out.println("token = " + JwtUtils.getJwtToken(user));
    }
    @Test
    public void verify(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyQWNjb3VudCI6IjY3MjAyNSIsImlzcyI6IjY3MjAyNSIsInVzZXJOYW1lIjoi546L55SzIiwiZXhwIjoxNjAwODI0NDI0LCJpYXQiOjE2MDA4MjI2MjR9.9ZwChmdCy3Y2qboLyXhfEt7UaFXYH0lDDC8KgxJRzfo";
        System.out.println("你的token值=" + JwtUtils.checkTokenAccount(token));
    }

}