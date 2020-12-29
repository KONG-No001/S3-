package com.gp.service;

import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;

import java.util.List;

public interface DingDanService {
    //id查询
    public DingDan chaXunByid(Integer id);

    //添加订单并返回自增id
    public Integer addDingDan(DingDan dingDan);

    //给订单里追加商品数据
    public Integer addWarehouse(Integer did,Integer wid,Integer count);

    //根据状态查询订单
    public List<DingDan> chaXunByzt(Integer zt);

    //查询不同状态的订单
    public DingDan daifukuan(Integer zt);
    public DingDan daifahuo(Integer zt);
    public DingDan daitihuo(Integer zt);
    public DingDan yitihuo(Integer zt);
}
