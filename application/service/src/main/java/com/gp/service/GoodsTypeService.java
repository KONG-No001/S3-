package com.gp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.GoodsTypeVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
public interface GoodsTypeService extends IService<GoodsTypeVo> {

    Page<GoodsTypeVo> fenYe(Integer page, Integer row, GoodsTypeVo goodsTypeVo);
}
