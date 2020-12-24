package com.gp.dao;

import com.gp.vo.GoodsCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCarDao {

    //条件查询
    public List<GoodsCar> chaXunByTJ(Integer uid);

    //添加
    public int tianJia(GoodsCar goodsCar);

    //删除
    public int shanChu(int id);

    //修改
    public int xiuGai(@Param(value = "sl") Integer sl, @Param(value = "id") Integer id);

}
