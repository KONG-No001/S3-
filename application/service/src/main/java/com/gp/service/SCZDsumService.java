package com.gp.service;



import com.gp.vo.PageVo;
import com.gp.vo.SCZDsum;

import java.util.List;

public interface SCZDsumService {

    //查所有
    public List<SCZDsum> querySCZDsumAll(SCZDsum sczDsum, int page, int rows);

    //数量
    public PageVo<SCZDsum> SCZDsumPageVo(SCZDsum sczDsum, int page, int rows);
    //根据id查
    public SCZDsum querySCZDsumId(int id);
    //添加
    public int addSCZDsum(SCZDsum sczDsum);
    //根据id删
    public int deleteSCZDsum(int id);
    //修改
    public int updateSCZDsum(SCZDsum sczDsum);
}
