package com.gp.service;

import com.gp.vo.DingDan;

import java.util.List;

public interface DingDanService {
    //id查询
    public DingDan chaXunByid(Integer id);

    //添加订单并返回自增id
    public Integer addDingDan(DingDan dingDan);

    //给订单里追加商品数据
    public Integer addWarehouse(Integer did,Integer wid,Integer count);

    //根据状态查询订单
    public List<DingDan> chaXunByzt(Integer zt,Integer uid);

    //付款成功
    int fk(int id);

    //已发货
    int fh(int id);

    //已收货
    int sh(int id);

    //查询不同状态的订单
    public List<DingDan> daifukuan(Integer zt);
    public List<DingDan> daifahuo(Integer zt);
    public List<DingDan> daitihuo(Integer zt);
    public List<DingDan> yitihuo(Integer zt);
}
