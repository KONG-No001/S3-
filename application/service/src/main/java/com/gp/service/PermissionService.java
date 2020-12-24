package com.gp.service;

import com.gp.vo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> listRole();

    Boolean delete(int id);
    Boolean add(Permission perm);
    Boolean update(Permission perm);


}
