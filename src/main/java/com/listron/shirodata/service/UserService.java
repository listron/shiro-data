package com.listron.shirodata.service;

import com.listron.shirodata.pojo.User;

public interface UserService {
    User queryUserByName(String name);
}
