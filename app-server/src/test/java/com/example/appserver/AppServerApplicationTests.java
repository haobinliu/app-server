package com.example.appserver;

import com.example.appserver.common.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppServerApplicationTests {

    @Autowired
    private TokenManager tokenManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void tokenPru(){

        System.out.println(this.tokenManager.createToken(200));
    }

}
