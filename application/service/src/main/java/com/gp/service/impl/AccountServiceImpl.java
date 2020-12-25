package com.gp.service.impl;

import com.gp.dao.AccountDao;
import com.gp.service.AccountService;
import com.gp.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public List<Account> queryAll() {
        return accountDao.queryAll();
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
