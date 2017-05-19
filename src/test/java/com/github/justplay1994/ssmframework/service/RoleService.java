package com.github.justplay1994.ssmframework.service;

import org.springframework.context.annotation.Role;

/**
 * Created by JustPlay1994 on 2017/5/18.
 * https://github.com/JustPlay1994/daily-log-manager
 */
public class RoleService {
    //创建角色
    public Role createRole(Role role){
        return null;
    }

    //删除角色
    public void deleteRole(Long roleId){

    }
    //添加角色-权限之间的关系
    public void correlationPermissions(Long roleId,Long... permissionIds){

    }
    //移除角色-权限之间的关系
    public void uncorrelationPermissions(Long roleId,Long... permissionIds){

    }
}
