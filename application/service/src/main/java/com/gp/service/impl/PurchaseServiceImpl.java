package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gp.dao.PurchaseDao;
import com.gp.dao.PurchaseGoodsDao;
import com.gp.service.PurchaseService;
import com.gp.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseVo> implements PurchaseService {

    @Autowired
    PurchaseDao purchaseDao;
    @Autowired
    PurchaseGoodsDao purchaseGoodsDao;

    //分页
    @Override
    public Page<PurchaseVo> fenYe(Integer page, Integer row, PurchaseVo purchaseVo) {
        Page<PurchaseVo> voPage=new Page<>(page,row);
        return purchaseDao.selectPageVo(voPage,purchaseVo);
    }

    //添加
    @Override
    public int add(PurchaseVo purchaseVo, List<Integer> goodsIds) {
        //添加到采购单和商品关联表
        purchaseGoodsDao.add(purchaseVo.getId(),goodsIds);
        //添加到采购单记录表
        return purchaseDao.insert(purchaseVo);
    }

    //删除
    @Override
    public int delete(int id, int purchaseId){
        //删除关联表信息
        purchaseGoodsDao.deleteByPurchaseId(purchaseId);
        //删除主表信息
        return purchaseDao.deleteById(id);
    }
    //审核
    @Override
    public int sp(int id, int status){
        return purchaseDao.sp(id,status);
    }
}
