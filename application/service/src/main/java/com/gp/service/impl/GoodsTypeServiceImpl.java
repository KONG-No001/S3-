package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.GoodsTypeVo;
import com.gp.dao.GoodsTypeDao;
import com.gp.service.GoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeDao, GoodsTypeVo> implements GoodsTypeService {
    //分页
    @Autowired
    GoodsTypeDao goodsTypeDao;

    public Page<GoodsTypeVo> fenYe(Integer page, Integer row, GoodsTypeVo goodsTypeVo) {
        //初始化page
        Page<GoodsTypeVo> pageVo = new Page<>(page,row);

        return  goodsTypeDao.selectPageVo(pageVo,goodsTypeVo);
    }
}
