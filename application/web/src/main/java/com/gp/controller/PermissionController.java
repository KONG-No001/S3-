package com.gp.controller;

import com.gp.service.PermissionService;
import com.gp.vo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("perm/list.action")
    @ResponseBody
    public List<Permission> list(){
        return permissionService.listRole();
    }

    @RequestMapping(value = "perm/delete.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@RequestBody Map<String,Object> params){
        return permissionService.delete((int)params.get("id"));
    }

    @RequestMapping(value = "perm/add.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean add(@RequestBody Permission perm){
        return permissionService.add(perm);
    }

    @RequestMapping(value = "perm/update.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(@RequestBody Permission perm){
        return permissionService.update(perm);
    }

}
