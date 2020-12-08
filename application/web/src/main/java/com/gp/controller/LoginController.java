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
    public Boolean login(String name, String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            System.out.println(subject.isAuthenticated());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("test.action")
    @ResponseBody
    public Boolean test(String name, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isAuthenticated());
        return subject.isAuthenticated();
    }

}
