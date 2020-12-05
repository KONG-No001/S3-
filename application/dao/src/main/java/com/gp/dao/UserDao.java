package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.User;

import java.util.Set;

public interface UserDao extends BaseMapper<User> {

    public User getUser(String userName);
    public Set<String> listRoles(String userName);
    public Set<String> listPermissions(String userName);


}
