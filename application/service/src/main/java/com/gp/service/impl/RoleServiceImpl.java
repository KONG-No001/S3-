package com.gp.service.impl;

import com.gp.dao.RoleDao;
import com.gp.service.RoleService;
import com.gp.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> listRole(Role role) {
        return roleDao.selectList(null);
    }
}
