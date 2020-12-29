package com.gp.service.impl;

import com.gp.dao.AccountDao;
import com.gp.dao.DingDanDao;
import com.gp.service.DingDanService;
import com.gp.vo.Account;
import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DingDanServiceImpl implements DingDanService {

    @Autowired
    DingDanDao dingDanDao;
    @Autowired
    AccountDao accountDao;

    @Override
    public DingDan chaXunByid(Integer id) {
        return dingDanDao.chaXunByid(id);
    }

    @Override
    public Integer addDingDan(DingDan dingDan) {
        dingDanDao.addDingDan(dingDan);
        Integer did=dingDan.getId();
        return did;
    }

    @Override
    public Integer addWarehouse(Integer did, Integer wid, Integer count) {
        return dingDanDao.addWarehouse(did,wid,count);
    }

    @Override
    public List<DingDan> chaXunByzt(Integer zt,Integer uid) {
        return dingDanDao.chaXunByzt(zt,uid);
    }

    @Override
    public int fk(int id) {

        DingDan dingDan=dingDanDao.chaXunByid(id);

        //添加到账单表
        Account account=new Account();
        account.setOrderId(dingDan.getId());
        account.setPurchaseId(null);
        account.setTotal(dingDan.getSum());
        account.setTime(dingDan.getTime());
        account.setAccountType(0);
        accountDao.add(account);

        return dingDanDao.fk(id);
    }


}
