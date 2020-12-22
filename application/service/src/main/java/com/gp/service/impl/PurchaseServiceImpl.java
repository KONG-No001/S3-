package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gp.dao.PurchaseDao;
import com.gp.dao.PurchaseGoodsDao;
import com.gp.dao.WarehouseDao;
import com.gp.service.PurchaseService;
import com.gp.vo.PurchaseGoodsVo;
import com.gp.vo.PurchaseVo;
import com.gp.vo.WarehouseVo;
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
    @Autowired
    WarehouseDao warehouseDao;

    //分页
    @Override
    public Page<PurchaseVo> fenYe(Integer page, Integer row, PurchaseVo purchaseVo) {
        Page<PurchaseVo> voPage=new Page<>(page,row);
        return purchaseDao.selectPageVo(voPage,purchaseVo);
    }

    //添加
    @Override
    public int add(PurchaseVo purchaseVo, List<PurchaseGoodsVo> purchaseGoodsVo) {
        //添加到采购单记录表
        purchaseDao.insert(purchaseVo);
        //添加到采购单和商品关联表
        return purchaseGoodsDao.add(purchaseVo.getId(),purchaseGoodsVo);
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
        if(status==1){
            //添加到仓库
            List<PurchaseGoodsVo> list=purchaseGoodsDao.queryByPurchaseId(id);
            for (int i = 0; i < list.size(); i++) {
                WarehouseVo warehouseVo=new WarehouseVo();
                //赋值
                warehouseVo.setGoodsId(list.get(i).getGoodsId());
                warehouseVo.setName(list.get(i).getGoodsVo().getGoodsName());
                warehouseVo.setGoodsTypeId(list.get(i).getGoodsVo().getGoodsTypeId());
                warehouseVo.setGoodsOutPrice(list.get(i).getGoodsVo().getGoodsInPrice());
                warehouseVo.setCount(list.get(i).getCount());
                warehouseVo.setStatus(0);
                warehouseDao.insert(warehouseVo);
            }
        }
        return purchaseDao.sp(id,status);
    }
}
