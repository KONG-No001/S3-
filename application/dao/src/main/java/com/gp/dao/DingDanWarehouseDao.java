package com.gp.dao;

import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;

import java.util.List;

public interface DingDanWarehouseDao {

    //根据订单id查询
    public List<DingDanWarehouse> chaXunByid(Integer did);

}
