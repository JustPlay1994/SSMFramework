package com.github.justplay1994.ssmframework;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by JustPlay1994 on 2017/5/17.
 * https://github.com/JustPlay1994/daily-log-manager
 */
public class HZZRealm extends AuthorizingRealm{

    public String getName(){
        return "myrealm1";
    }

    //        Authentication; 认证
    //        Authorizing; 授权

    //    用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //第一步，从token中取出用户发送过来的身份信息
        String str = (String)token.getPrincipal();

        //第二步，根据用户输入的账号从数据库查询密码
        String pwd = "123";

        //如果查询不到返回null
        //如果查询到，返回认证信息：AuthenticationInfo

        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(str,pwd,getName());

        return simpleAuthenticationInfo;
    }
}
