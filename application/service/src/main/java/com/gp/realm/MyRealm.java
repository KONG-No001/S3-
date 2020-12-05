package com.gp.realm;

import com.gp.dao.UserDao;
import com.gp.vo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UsernamePasswordToken token = (UsernamePasswordToken) principalCollection.getPrimaryPrincipal();

        //获取用户名
        String userName = token.getUsername();

        //获取角色权限
        Set<String> roles = userDao.listRoles(userName);
        Set<String> perms = userDao.listPermissions(userName);

        //授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //从数据库获取user
        User user = userDao.getUser(token.getUsername());

        //断言是否存在该用户
        if (user==null) throw new UnknownAccountException("从数据库里查找不到该用户！！！");

        //获取密码以及其他加密信息
        String password = user.getPassword();

        //返回
        return new SimpleAuthenticationInfo(
            token, password, getName()
        );
    }
}
