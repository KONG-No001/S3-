package com.gp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gp.vo.PurchaseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface PurchaseService extends IService<PurchaseVo> {

        Page<PurchaseVo> fenYe(Integer page,Integer row,PurchaseVo purchaseVo);

        int add(PurchaseVo purchaseVo, List<Integer> goodsIds);

        int delete(int id, int purchaseId);

        int sp(int id, int status);
}
