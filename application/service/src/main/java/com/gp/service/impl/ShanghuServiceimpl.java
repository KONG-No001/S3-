package com.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.gp.dao.ShanghuDao;
import com.gp.service.ShanghuService;
import com.gp.vo.PageVo;
import com.gp.vo.Shanghu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShanghuServiceimpl implements ShanghuService {
    @Autowired
    ShanghuDao shanghuDao;

    @Override
    public List<Shanghu> queryShanghuAll(Shanghu shanghu, int page, int rows) {
        PageHelper.startPage(page,rows);
        return shanghuDao.queryShanghuAll(shanghu);

    }

    @Override
    public PageVo<Shanghu> ShanghuPageVo(Shanghu shanghu, int page, int rows) {
        PageVo<Shanghu> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(shanghuDao.queryShanghuAll(shanghu));
        pageVo.setTotal(shanghuDao.ShanghuCount(shanghu));
        return pageVo;
    }

    @Override
    public Shanghu queryShanghuId(int id) {
        return shanghuDao.queryShanghuId(id);
    }

    @Override
    public int addShanghu(Shanghu shanghu) {
        return shanghuDao.addShanghu(shanghu);
    }

    @Override
    public int deleteShanghu(int id) {
        return shanghuDao.deleteShanghu(id);
    }

    @Override
    public int updateShanghu(Shanghu shanghu) {
        return shanghuDao.updateShanghu(shanghu);
    }
}
