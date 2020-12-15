package com.gp.controller;

import com.gp.service.RoleService;
import com.gp.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/role/list.action")
    @ResponseBody
    public List<Role> listRole(){
        return roleService.listRole(null);
    }

}
