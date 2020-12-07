package com.gp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("login.action")
    @ResponseBody
    public String login(String name, String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            session.setAttribute("userName",name);
            return "登录成功！！！";
        }catch (Exception e){
            e.printStackTrace();
            return "登录失败！！！";
        }

    }

}
