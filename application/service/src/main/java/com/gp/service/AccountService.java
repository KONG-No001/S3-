package com.gp.service;

import com.gp.vo.Account;
import com.gp.vo.PageVo;

import java.util.List;

public interface AccountService {
    //查所有
    List<Account> queryAll(int page,int rows);
    PageVo<Account> AccountPageVo(int page,int rows);

    //查采购
    List<Account> queryPurchase();

    //查销售
    List<Account> queryOrder();
}
