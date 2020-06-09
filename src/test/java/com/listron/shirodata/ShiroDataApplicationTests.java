package com.listron.shirodata;

import com.listron.shirodata.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroDataApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {
        System.out.print(userService.queryUserByName("zhangsan"));
    }

}
