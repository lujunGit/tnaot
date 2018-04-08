package com.mongcent.tnaot.auth;

import com.mongcent.tnaot.model.Permission;
import com.mongcent.tnaot.model.Role;
import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.service.UserServiceV1;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

    //用于用户查询
    @Autowired
    private UserServiceV1 userServiceV1;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();

        logger.info("登陆账号：" + name);

        //查询用户名称
        User user = userServiceV1.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            logger.info("账号角色：" + role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
                logger.info("角色权限：" + permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = userServiceV1.getUserByName(name);
        logger.info("认证账号：" + name);

        //int lockRandom = new Random().nextInt(1);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        }else{
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
                    user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
