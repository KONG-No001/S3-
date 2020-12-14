package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gp.dao.GoodsDao;
import com.gp.service.GoodsService;
import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsVo> implements GoodsService {
    //分页
    @Autowired
    GoodsDao goodsDao;

    public Page<GoodsVo> fenYe(Integer page, Integer row, GoodsVo goodsVo) {
        //初始化page
        Page<GoodsVo> pageVo = new Page<>(page,row);

        return  goodsDao.selectPageVo(pageVo,goodsVo);
    }

}
