package com.gp.dao;

import com.gp.vo.PurchaseGoodsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.PurchaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface PurchaseGoodsDao extends BaseMapper<PurchaseGoodsVo> {
        int add(@Param("purchaseId") int purchaseId, @Param("purchaseGoods")List<PurchaseGoodsVo> purchaseGoodsVo);

        int deleteByPurchaseId(int id);

        List<PurchaseGoodsVo> queryByPurchaseId(int id);
}
