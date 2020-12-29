package com.gp.service.impl;

import com.gp.dao.AccountDao;
import com.gp.dao.DeliveryDao;
import com.gp.dao.DingDanDao;
import com.gp.service.DeliveryService;
import com.gp.service.DingDanService;
import com.gp.vo.Account;
import com.gp.vo.DeliveryVo;
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
    @Autowired
    DeliveryDao deliveryDao;

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

    @Override
    public int fh(int id) {
        //通知仓库发货 添加到仓库表
        DingDan dingDan=dingDanDao.chaXunByid(id);
        DeliveryVo deliveryVo=new DeliveryVo();
        deliveryVo.setDingdanId(dingDan.getId());
        deliveryVo.setUserId(dingDan.getUser().getId());
        deliveryVo.setShopId(dingDan.getShanghu().getId());
        deliveryVo.setTime(dingDan.getTime());
        deliveryVo.setStatus(0);
        deliveryDao.insert(deliveryVo);

        return dingDanDao.fh(id);
    }

    @Override
    public int sh(int id) {
        DeliveryVo deliveryVo=deliveryDao.queryByDingdanId(id);
        dingDanDao.sh(deliveryVo.getId());
        return dingDanDao.sh(id);
    }

    @Override
    public List<DingDan> daifukuan(Integer zt) {
        return dingDanDao.daifukuan(zt);
    }

    @Override
    public List<DingDan> daifahuo(Integer zt) {
        return dingDanDao.daifahuo(zt);
    }

    @Override
    public List<DingDan> daitihuo(Integer zt) {
        return dingDanDao.daitihuo(zt);
    }

    @Override
    public List<DingDan> yitihuo(Integer zt) {
        return dingDanDao.yitihuo(zt);
    }


}
