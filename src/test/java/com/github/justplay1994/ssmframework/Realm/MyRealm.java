package com.github.justplay1994.ssmframework.Realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by JustPlay1994 on 2017/5/15.
 * https://github.com/JustPlay1994/daily-log-manager
 */
public class MyRealm implements Realm{

    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 UsernamePasswordToken 类型的 Token
        return token instanceof UsernamePasswordToken;
    }

//    用于认证
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();//得到用户名
        String password = new String((char[])token.getCredentials());//得到密码
        if(!"hzz".equals(username)){
            throw new UnknownAccountException();//如果用户名错误
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();//如果密码错误
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现
        return new SimpleAuthenticationInfo("hzz@123",password,getName());
    }
}
