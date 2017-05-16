package com.github.justplay1994.ssmframework;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


/**
 * Created by JustPlay1994 on 2017/5/15.
 * https://github.com/JustPlay1994/daily-log-manager
 */

public class Login {
    Logger logger = LogManager.getLogger(Login.class);

    @Test
    public void testLogin1(){
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager>factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hzz","123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println(e);
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }

    @Test
    public void testLogin2(){
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager>factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hzz","123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println(e);
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }


    public Subject getSubject(String file,String username,String password){
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager>factory =
                new IniSecurityManagerFactory(file);
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        return subject;

    }

    @Test
    public void testHasRole(){
        String username = "hzz";
        String password = "123";
        Subject subject = getSubject("classpath:shiro.ini",username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e1){
            logger.error(e1);
        }
        logger.info("123");
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //判断拥有角色：role1
        Assert.assertTrue(subject.hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject.hasRoles(Arrays.asList("role1","role2","role3"));
        Assert.assertEquals(true,result[0]);
        Assert.assertEquals(true,result[1]);
        Assert.assertEquals(false,result[2]);

        subject.logout();
    }
}
