package com.github.justplay1994.ssmframework.service;

import com.github.justplay1994.ssmframework.dao.User;

import java.util.Set;

/**
 * Created by JustPlay1994 on 2017/5/18.
 * https://github.com/JustPlay1994/daily-log-manager
 */
public class UserService {

    public User createUser(User user){
        return null;
    }
    public void changePassword(Long userId,Long... roleIds){

    }
    //添加用户-角色关系
    public void correlationRoles(Long userId,Long... rolesIds){

    }
    //移除用户-角色关系
    public void uncorrelationRoles(Long userId,Long... rolesIds){

    }
    //根据用户名查找用户
    public User findByUsernames(String username){
        return null;
    }
    //根据用户名查找其他角色
    public Set<String> findRoles(String username){
        return null;
    }
    //根据用户名查找其权限
    public Set<String> findPermissions(String username){
        return null;
    }
}
