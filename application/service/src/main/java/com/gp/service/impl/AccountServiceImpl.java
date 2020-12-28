package com.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.gp.dao.AccountDao;
import com.gp.service.AccountService;
import com.gp.vo.Account;
import com.gp.vo.DingDan;
import com.gp.vo.PageVo;
import com.gp.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    //所有订单
    @Override
    public List<Account> queryAll() {
        return accountDao.queryAll();
    }

    @Override
    public PageVo<Account> fenYe(Account account,int page, int rows) {
        PageVo<Account> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(accountDao.fenYe(account));
        pageVo.setTotal(accountDao.queryCount(account));
        return pageVo;


    }

    @Override
    public PageVo<Account> fenYe1(Account account, int page, int rows) {
        PageVo<Account> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(accountDao.fenYe1(account));
        pageVo.setTotal(accountDao.queryCount1(account));
        return pageVo;
    }

    @Override
    public PageVo<Account> fenYe2(Account account, int page, int rows) {
        PageVo<Account> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(accountDao.fenYe2(account));
        pageVo.setTotal(accountDao.queryCount2(account));
        return pageVo;
    }

}
