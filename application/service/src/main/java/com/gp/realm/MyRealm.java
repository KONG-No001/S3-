package com.gp.realm;

import com.gp.dao.StaffDao;
import com.gp.vo.Staff;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    StaffDao staffDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UsernamePasswordToken token = (UsernamePasswordToken) principalCollection.getPrimaryPrincipal();

        //获取用户名
        String staffName = token.getUsername();

        //获取角色权限
        Set<String> roles = staffDao.listRoles(staffName);
        Set<String> perms = staffDao.listPermissions(staffName);

        //授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //从数据库获取staff
        Staff staff = staffDao.getStaff(token.getUsername());

        //断言是否存在该用户
        if (staff==null) throw new UnknownAccountException("从数据库里查找不到该用户！！！");

        //获取密码以及其他加密信息
        String password = staff.getPassword();

        //返回
        return new SimpleAuthenticationInfo(
            token, password, getName()
        );
    }
}
