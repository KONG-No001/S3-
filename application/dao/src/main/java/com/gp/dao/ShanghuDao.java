package com.gp.dao;



import com.gp.vo.Shanghu;

import java.util.List;

public interface ShanghuDao {

    //查所有
    public List<Shanghu> queryShanghuAll(Shanghu shanghu);

    //数量
    public int ShanghuCount(Shanghu shanghu);
    //根据id查
    public Shanghu queryShanghuId(int id);
    //添加
    public int addShanghu(Shanghu shanghu);
    //根据id删
    public int deleteShanghu(int id);
    //修改
    public int updateShanghu(Shanghu shanghu);
}
