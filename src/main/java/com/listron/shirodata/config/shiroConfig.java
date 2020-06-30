package com.listron.shirodata.config;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();


        //添加shiro的内置过滤器
        /*
        anon: 无需认证就可以访问
        authc: 必须认证才能访问
        user: 必须拥有 "记住我"才能访问
        perms: 拥有对某个资源的权限才能访问
        role: 拥有某个角色的权限才能访问
        * */
        Map<String,String> filterMap = new LinkedHashMap<>();

        //授权,未授权跳转到未授权页面,不同的界面有不同的权限，根据数据库的来源，判断当前用户有什么权限，
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");


        System.out.print("检测认证界面/user/*");
        //对所有/user/*的增加了authc认证限制，登陆前，每次访问，都会进行检测，除非登陆后，才有权限
        filterMap.put("/user/*","authc");




        bean.setFilterChainDefinitionMap(filterMap);

        //没有认证，进入登陆页，设置登陆的请求
        bean.setLoginUrl("/toLogin");

        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //未授权，跳转到未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return  bean;

    }

    //DefaultWebSecuryManager
    @Bean(name ="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //create realm
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
