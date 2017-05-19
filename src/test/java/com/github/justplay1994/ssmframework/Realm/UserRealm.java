package com.github.justplay1994.ssmframework.Realm;

import com.github.justplay1994.ssmframework.dao.User;
import com.github.justplay1994.ssmframework.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by JustPlay1994 on 2017/5/18.
 * https://github.com/JustPlay1994/daily-log-manager
 */
public class UserRealm extends AuthorizingRealm {
    private UserService userService = new UserService();
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.findByUsernames(username);
        if(user == null){
            throw new UnknownAccountException();
        }
//        if (Boolean.TRUE.equals(user.getLocked())){
//            throw  new LockedAccountException();
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getPassword()),
                getName()
        );
        return authenticationInfo;
    }
}
