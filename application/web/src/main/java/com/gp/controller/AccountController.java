package com.gp.controller;


import com.gp.service.AccountService;
import com.gp.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;

    //查所有
    @RequestMapping("/queryAll.action")
    public List<Account> queryAll(){
        return service.queryAll();
    }

    //查采购
    @RequestMapping("/queryPurchase.action")
    public List<Account> queryPurchase(){
        return service.queryPurchase();
    }

    //查销售
    @RequestMapping("/queryOrder.action")
    public List<Account> queryOrder(){
        return service.queryOrder();
    }

}
