package com.gp.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    GoodsService goodsService;

    public Page<GoodsVo> fenYe(Integer page, Integer row, GoodsVo goodsVo) {
        //初始化page
        Page<GoodsVo> pageVo = new Page<>(page,row);
        //设置条件
        QueryWrapper<GoodsVo> wrapper =new QueryWrapper<>();

        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询
        if(!StringUtils.isEmpty(goodsVo.getGoodsName())){
            wrapper.like("name",goodsVo.getGoodsName());
        }
        if(!StringUtils.isEmpty(goodsVo.getGoodsCode())){
            wrapper.like("name",goodsVo.getGoodsCode());
        }
        return  goodsService.page(pageVo,wrapper);
    }

}
