package com.gp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("login.action")
    @ResponseBody
    public Boolean login(String name, String password, boolean rememberMe){
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        System.out.println(rememberMe);
        token.setRememberMe(rememberMe);

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
    public Map<String,Object> test(String name){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession().getAttribute("name"));
        subject.getSession().setAttribute("name",name);
        Map<String,Object> map = new HashMap<>();

        map.put("isAuth",subject.isAuthenticated());
        map.put("isReMe",subject.isRemembered());

        return map;
    }

}
