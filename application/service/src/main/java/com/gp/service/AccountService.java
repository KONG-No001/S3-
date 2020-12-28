package com.gp.service;

import com.gp.vo.Account;
import com.gp.vo.DingDan;
import com.gp.vo.PageVo;
import com.gp.vo.PurchaseVo;

import java.util.List;

public interface AccountService {
    //查所有
    List<Account> queryAll();

    PageVo<Account> fenYe(Account account, int page, int rows);

    //查采购
    PageVo<Account> fenYe1(Account account, int page, int rows);

    //查销售
    PageVo<Account> fenYe2(Account account, int page, int rows);


}
