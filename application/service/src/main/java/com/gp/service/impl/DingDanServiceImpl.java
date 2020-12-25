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
}
