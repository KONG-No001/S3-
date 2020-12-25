package com.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.gp.dao.AccountDao;
import com.gp.service.AccountService;
import com.gp.vo.Account;
import com.gp.vo.PageVo;
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
    public List<Account> queryAll(int page,int rows) {
        PageHelper.startPage(page,rows);
        return accountDao.queryAll();
    }

    @Override
    public PageVo<Account> AccountPageVo(int page, int rows) {
        PageVo<Account> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(accountDao.queryAll());
        pageVo.setTotal(accountDao.querycount());
        return pageVo;
    }

    //采购订单
    @Override
    public List<Account> queryPurchase() {
        List<Account> list= accountDao.queryAll();
        List<Account> accounts=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccountType()==0){
                accounts.add(list.get(i));
            }
        }
        return accounts;
    }

    //销售订单
    @Override
    public List<Account> queryOrder() {

        List<Account> list= accountDao.queryAll();
        List<Account> accounts=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccountType()==1){
                accounts.add(list.get(i));
            }
        }
        return accounts;
    }
}
