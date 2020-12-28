package com.gp.service;

import com.gp.vo.GoodsCar;

import java.util.List;

public interface GoodsCarService {

    //条件查询
    public List<GoodsCar> chaXunByTJ(Integer uid);

    //添加
    public int tianJia(GoodsCar goodsCar);

    //删除
    public int shanChu(int id);

    //删除订单商品
    public int shanChuDingDan(int id);

    //修改
    public int xiuGai(Integer sl,Integer id);

    //查询是否有同类商品
    public GoodsCar chaXunBy(Integer uid,Integer wid);
}
