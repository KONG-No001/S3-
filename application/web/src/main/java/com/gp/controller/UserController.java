package com.gp.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gp.service.UserService;
import com.gp.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    UserService service;


    @RequestMapping("/queryUser.action")
    @ResponseBody
    @CrossOrigin
   public User UserLogin(User user){
        User user1=service.queryUser(user);
        return user1;
    }

    @RequestMapping("/queryUserByName.action")
    @ResponseBody
    @CrossOrigin
    public User queryById(User user){
        return service.queryUserByName(user);
    }


    @RequestMapping("/addUser.action")
    @ResponseBody
    @CrossOrigin
    public String addUser(User user){

        int row = service.addUser(user);

        return row>0?"注册成功":"注册失败";
    }
}
