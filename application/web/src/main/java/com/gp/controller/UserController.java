package com.gp.controller;

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

    @RequestMapping("user/listUser.action")
    @ResponseBody()
    public List<User> listUesr(String type,String value){
        return userService.listUser("user_"+type,value);
    }

    @RequestMapping("user/test")
    @ResponseBody()
    public User testUser(User user){
        return user;
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


}
