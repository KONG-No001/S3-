package com.gp.dao;

import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DingDanDao {

    //id查询
    public DingDan chaXunByid(Integer id);

    //添加订单并返回自增id
    public Integer addDingDan(DingDan dingDan);

    //给订单里追加商品数据
    public Integer addWarehouse(@Param(value = "did") Integer did,@Param(value = "wid") Integer wid,@Param(value = "count") Integer count);

    //根据状态查询订单
    public List<DingDan> chaXunByzt(Integer zt);
}
