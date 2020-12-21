package com.gp.controller;

import com.alibaba.fastjson.JSONObject;
import com.gp.service.UserService;
import com.gp.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "user/listUser.action",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String listUesr(String type,String value){
        return JSONObject.toJSONString(userService.listUser("user_"+type,value)) ;
    }


    @RequestMapping(value = "user/update.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUser(@RequestBody User data){
        return userService.updateUser(data);
    }

    @RequestMapping(value = "user/add.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean addUser(@RequestBody User data){
        return userService.addUser(data);
    }

    @RequestMapping(value = "user/remove.action",method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteUser(int id){
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "user/updateRoleRelation.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateRoleRelation(@RequestBody Map<String,Object> params){
        return userService.updateRoleRelation((int)params.get("userId"),(List<Integer>) params.get("roleIds")) ;
    }

}
