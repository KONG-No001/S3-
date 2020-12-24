package com.gp.service;

import com.gp.vo.Account;

import java.util.List;

public interface AccountService {
    //查所有
    List<Account> queryAll();

    //查采购
    List<Account> queryPurchase();

    //查销售
    List<Account> queryOrder();
}
