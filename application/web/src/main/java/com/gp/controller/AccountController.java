package com.gp.controller;


import com.alibaba.fastjson.JSONObject;
import com.gp.service.AccountService;
import com.gp.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;

    //查所有
    @ResponseBody
    @RequestMapping(value = "/queryAll.action",produces = {"application/json;charset=utf-8"})
    public String queryAll(){
        return JSONObject.toJSONString(service.queryAll());
    }

    //分页
    @ResponseBody
    @RequestMapping(value = "/fenYe.action",produces = {"application/json;charset=utf-8"})
    public String fenYe(Account account,@RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "rows",defaultValue = "5") int rows){
        return JSONObject.toJSONString(service.fenYe(account,page,rows));
    }

    //查采购
    @ResponseBody
    @RequestMapping(value = "/fenYe1.action",produces = {"application/json;charset=utf-8"})
    public String queryPurchase(Account account,
                                @RequestParam(value = "page",defaultValue = "1") int page,
                                @RequestParam(value = "rows",defaultValue = "5") int rows){

        return JSONObject.toJSONString(service.fenYe1(account,page,rows)) ;
    }

    //查销售
    @ResponseBody
    @RequestMapping(value="/fenYe2.action",produces = {"application/json;charset=utf-8"})
    public String queryOrder(Account account,@RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "rows",defaultValue = "5") int rows){
        return JSONObject.toJSONString(service.fenYe2(account,page,rows));
    }

}
