package com.listron.shirodata.controller;

import com.listron.shirodata.mapper.UserMapper;
import com.listron.shirodata.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("user/queryUserList")
    public List<User> queryUserList(){
        List<User> list = userMapper.queryUserl();
        for (User user:list){
            System.out.print(user);
        }
        //测试AtomicLong
//        AtomicLong atomicLong2 = new AtomicLong(3);
//        long s= atomicLong2.getAndAdd(3);
//        System.out.println("s：" + s);
//        System.out.println("Value1：" + atomicLong2.get());
//
//        AtomicLong atomicLong3 = new AtomicLong(10);
//        atomicLong3.compareAndSet(10,15);
//        System.out.println("Value2：" + atomicLong3.get());


        return list;
    }

    @GetMapping("user/addUser")
    public String addUser(){
        userMapper.addUser(new User(7,"张三三","345678","add"));
        return "ok";
    }
    @GetMapping("user/queryUserById")
    public User queryUserById(){
        User user = userMapper.queryUserById(7);
        System.out.print(user);
        return user;
    }
    @GetMapping("user/queryUserByName")
    public User queryUserByName(String name){
        User user = userMapper.queryUserByName(name);
        System.out.print(user);
        return user;
    }

    @GetMapping("user/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(7);
        return "OK";
    }
}
