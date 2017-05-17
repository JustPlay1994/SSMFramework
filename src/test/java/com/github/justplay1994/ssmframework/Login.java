package com.github.justplay1994.ssmframework;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
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

    /**
     * 认证过程, 使用shiro.ini
     */
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

    /**
     * 认证过程, 使用shiro-realm.ini
     */
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

    /**
     * 得到一个Subject,后续用于验证token
     * @param file
     * @return
     */
    public Subject getSubject(String file){
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

    /**
     * 判断角色
     */
    @Test
    public void testHasRole(){
        String username = "hzz";
        String password = "123";
        Subject subject = getSubject("classpath:shiro.ini");
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
        logger.info(subject.hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        logger.info(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject.hasRoles(Arrays.asList("role1","role2","role3"));
        logger.info(result[0]+ " "+ result[1]+" "+result[2]);
        Assert.assertEquals(true,result[0]);
        Assert.assertEquals(true,result[1]);
        Assert.assertEquals(false,result[2]);

        subject.logout();
    }

    /**
     *  网上的认证过程, 使用shiro-realm.ini
     */
    @Test
    public void testLoginAndLogout(){
        //创建securityManager工厂，通过Ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //创建SecurityManager
        SecurityManager sm = factory.getInstance();

        //将securityManager设置到当前的环境中
        SecurityUtils.setSecurityManager(sm);

        //从SecurityUtils里面创建一个subject
        Subject subject = SecurityUtils.getSubject();

        //在认证提交前，需要准备token(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken("hzz", "123");

        try {
            //执行认证提交
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();

        System.out.println("是否认证通过：" + isAuthenticated);


        //得到一个身份集合，其包含了 Realm 验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());


        //退出操作
        subject.logout();

        //是否认证通过
        isAuthenticated = subject.isAuthenticated();

        System.out.println("是否认证通过：" + isAuthenticated);


    }//method

    /**
     * 判断权限
     */
    @Test
    public void testRight(){
        String username = "hzz";
        String password = "123";
        Subject subject = getSubject("classpath:shiro.ini");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e1){
            logger.error(e1);
        }
        //判断拥有权限： user:create
        Assert.assertTrue(subject.isPermitted("user:create"));
        logger.info(subject.isPermitted("user:create"));
        //判断拥有权限： user:update and user:delete
        Assert.assertTrue(subject.isPermittedAll("user:update","user:delete"));
        logger.info(subject.isPermittedAll("user:update","user:delete"));
        //判断没有权限： user:view
        Assert.assertFalse(subject.isPermitted("user:view"));
        logger.info(subject.isPermitted("user:view"));


        subject.logout();
    }
}
