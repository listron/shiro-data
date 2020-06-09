package com.listron.shirodata.config;

import com.listron.shirodata.pojo.User;
import com.listron.shirodata.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm  extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.print("授权doGetAuthorizationInfo");

        //都授权了user:add
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登陆user对象，设置当前用户的权限
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        System.out.print("currentUser.getPerms()="+currentUser.getPerms());
        info.addStringPermission(currentUser.getPerms());


        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.print("认证doGetAuthenticationInfo");

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //用户明、密码 数据库取
        User user= userService.queryUserByName(usernamePasswordToken.getUsername());
        if(user == null){
            return null;
        }

        //密码认证 shiro自己处理
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
