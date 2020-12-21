package com.gp.service;

import com.gp.vo.Role;

import java.util.List;

public interface RoleService {

    List<Role> listRole(Role role);

    boolean addRole(Role role);

    boolean updateRole(Role role);
    List<Role> showRole();

}
