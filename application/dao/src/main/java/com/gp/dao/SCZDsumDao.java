package com.gp.dao;



import com.gp.vo.SCZDsum;

import java.util.List;

public interface SCZDsumDao {

    //查所有
    public List<SCZDsum> querySCZDsumAll(SCZDsum sczDsum);

    //数量
    public int SCZDsumCount(SCZDsum sczDsum);
    //根据id查
    public SCZDsum querySCZDsumId(int id);
    //添加
    public int addSCZDsum(SCZDsum sczDsum);
    //根据id删
    public int deleteSCZDsum(int id);
    //修改
    public int updateSCZDsum(SCZDsum sczDsum);
}
