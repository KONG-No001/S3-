package com.gp.service.impl;

import com.gp.dao.DingDanDao;
import com.gp.service.DingDanService;
import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DingDanServiceImpl implements DingDanService {

    @Autowired
    DingDanDao dingDanDao;

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
    public List<DingDan> chaXunByzt(Integer zt) {
        return dingDanDao.chaXunByzt(zt);
    }

    @Override
    public DingDan daifukuan(Integer zt) {
        return dingDanDao.daifukuan(zt);
    }

    @Override
    public DingDan daifahuo(Integer zt) {
        return dingDanDao.daifahuo(zt);
    }

    @Override
    public DingDan daitihuo(Integer zt) {
        return dingDanDao.daitihuo(zt);
    }

    @Override
    public DingDan yitihuo(Integer zt) {
        return dingDanDao.yitihuo(zt);
    }


}
