package com.gp.dao;

import com.gp.vo.DingDan;
import com.gp.vo.DingDanWarehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DingDanDao {

    //id查询
    public DingDan chaXunByid(Integer id);
}
