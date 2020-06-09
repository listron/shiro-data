package com.listron.shirodata.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }

    //@RequiresPermissions("user:add")
    @RequestMapping("/user/add")//url的地址，可以自定义
    public String add(){
        //返回路径
        return "user/add";
    }
    //@RequiresPermissions("user:update")
    @RequestMapping("/user/update")//url的地址，可以自定义
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")//url的地址，可以自定义
    public String toLogin(){
        return "user/login";
    }


    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "user/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "user/login";
        }
    }


    @RequestMapping("/noauth")//url的地址，可以自定义
    public String unauth(){
        return "user/unauth";
    }
}
