package com.gp.service.impl;

import com.gp.dao.GoodsCarDao;
import com.gp.service.GoodsCarService;
import com.gp.vo.GoodsCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCarServiceImpl implements GoodsCarService {

    @Autowired
    GoodsCarDao goodsCarDao;

    @Override
    public List<GoodsCar> chaXunByTJ(Integer uid) {
        return goodsCarDao.chaXunByTJ(uid);
    }

    @Override
    public int tianJia(GoodsCar goodsCar) {
        return goodsCarDao.tianJia(goodsCar);
    }

    @Override
    public int shanChu(int id) {
        return goodsCarDao.shanChu(id);
    }

    @Override
    public int shanChuDingDan(int id) {
        return goodsCarDao.shanChuDingDan(id);
    }

    @Override
    public int xiuGai(Integer sl, Integer id) {
        return goodsCarDao.xiuGai(sl, id);
    }

    @Override
    public GoodsCar chaXunBy(Integer uid, Integer wid) {
        return goodsCarDao.chaXunBy(uid,wid);
    }
}
