package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gp.dao.PermissionDao;
import com.gp.service.PermissionService;
import com.gp.vo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> listRole() {
        return permissionDao.selectList(null);
    }

    @Override
    public Boolean delete(int id) {
        return permissionDao.deleteById(id)>0;
    }

    @Override
    public Boolean add(Permission perm) {
        return permissionDao.insert(perm)>0;
    }

    @Override
    public Boolean update(Permission perm) {
        return permissionDao.updateById(perm)>0;
    }
}
