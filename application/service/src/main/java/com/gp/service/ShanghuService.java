package com.gp.service;



import com.gp.vo.PageVo;
import com.gp.vo.Shanghu;

import java.util.List;

public interface ShanghuService {

    //查所有
    public List<Shanghu> queryShanghuAll(Shanghu shanghu, int page, int rows);
    //数量
    public PageVo<Shanghu> ShanghuPageVo(Shanghu shanghu, int page, int rows);
    //根据id查
    public Shanghu queryShanghuId(int id);
    //添加
    public int addShanghu(Shanghu shanghu);
    //根据id删
    public int deleteShanghu(int id);
    //修改
    public int updateShanghu(Shanghu shanghu);
}
