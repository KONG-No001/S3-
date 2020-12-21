package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
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

    @Override
    public List<Role> showRole(){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("role_id id","role_name name","role_type type");

        return roleDao.selectList(queryWrapper);
    }


    @Override
    public boolean addRole(Role role) {
        return roleDao.insert(role)>0;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleDao.updateById(role)>0;
    }
}
