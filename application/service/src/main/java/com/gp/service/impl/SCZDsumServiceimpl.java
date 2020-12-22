package com.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.gp.dao.SCZDsumDao;
import com.gp.service.SCZDsumService;
import com.gp.vo.PageVo;
import com.gp.vo.SCZDsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCZDsumServiceimpl implements SCZDsumService {
    @Autowired
    SCZDsumDao sczDsumDao;
    @Override
    public List<SCZDsum> querySCZDsumAll(SCZDsum sczDsum,int page, int rows) {
        PageHelper.startPage(page,rows);
        return sczDsumDao.querySCZDsumAll(sczDsum);
    }

    @Override
    public PageVo<SCZDsum> SCZDsumPageVo(SCZDsum sczDsum, int page, int rows) {
        PageVo<SCZDsum> pageVo=new PageVo<>();
        //放在查询代码前面
        PageHelper.startPage(page,rows);
        pageVo.setRows(sczDsumDao.querySCZDsumAll(sczDsum));
        pageVo.setTotal(sczDsumDao.SCZDsumCount(sczDsum));

        return pageVo;
    }

    @Override
    public SCZDsum querySCZDsumId(int id) {
        return sczDsumDao.querySCZDsumId(id);
    }

    @Override
    public int addSCZDsum(SCZDsum sczDsum) {
        return sczDsumDao.addSCZDsum(sczDsum);
    }

    @Override
    public int deleteSCZDsum(int id) {
        return sczDsumDao.deleteSCZDsum(id);
    }

    @Override
    public int updateSCZDsum(SCZDsum sczDsum) {
        return sczDsumDao.updateSCZDsum(sczDsum);
    }
}
