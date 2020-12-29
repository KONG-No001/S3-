package com.gp.dao;

import com.gp.vo.DingDan;
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
    public List<DingDan> chaXunByzt(@Param(value = "zt")Integer zt,@Param(value = "uid") Integer uid);

    //查询不同状态的订单
    public DingDan daifukuan(Integer zt);
    public DingDan daifahuo(Integer zt);
    public DingDan daitihuo(Integer zt);
    public DingDan yitihuo(Integer zt);

    //付款成功
    int fk(int id);

    //查询不同状态的订单
    List<DingDan> daifukuan(Integer zt);
    List<DingDan> daifahuo(Integer zt);
    List<DingDan> daitihuo(Integer zt);
    List<DingDan> yitihuo(Integer zt);
}
