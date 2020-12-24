package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gp.dao.AccountDao;
import com.gp.dao.PurchaseDao;
import com.gp.dao.PurchaseGoodsDao;
import com.gp.dao.WarehouseDao;
import com.gp.service.PurchaseService;
import com.gp.vo.Account;
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
    @Autowired
    AccountDao accountDao;

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
        int flag=purchaseDao.insert(purchaseVo);
        //添加到采购单和商品关联表
         purchaseGoodsDao.add(purchaseVo.getId(),purchaseGoodsVo);
        //添加到仓库
        List<PurchaseGoodsVo> list=purchaseGoodsDao.queryByPurchaseId(purchaseVo.getId());

        //添加到账单表
        Account account=new Account();
        account.setPurchaseId(purchaseVo.getId());
        account.setOrderId(null);
        account.setTotal(purchaseVo.getSum());
        account.setTime(purchaseVo.getTime());
        account.setAccountType(0);
        accountDao.add(account);

        for (int i = 0; i < list.size(); i++) {
            //仓库查这个商品
            WarehouseVo warehouseVo=warehouseDao.queryByGoodsId(list.get(i).getGoodsId());
            //判断仓库是有这个商品
            //如果没有
            WarehouseVo warehouseVo1=new WarehouseVo();
            if(warehouseVo==null){
                warehouseVo1.setGoodsId(list.get(i).getGoodsId());
                warehouseVo1.setName(list.get(i).getGoodsVo().getGoodsName());
                warehouseVo1.setGoodsTypeId(list.get(i).getGoodsVo().getGoodsTypeId());
                warehouseVo1.setGoodsOutPrice(list.get(i).getGoodsVo().getGoodsInPrice()*1.1);
                warehouseVo1.setCount(list.get(i).getCount());
                warehouseDao.insert(warehouseVo1);
            }else {
                //如果有
                warehouseVo.setCount(warehouseVo.getCount()+list.get(i).getCount());
                warehouseDao.updateById(warehouseVo);
            }

        }
        return flag;
    }

    @Override
    public List<PurchaseGoodsVo> queryByPurchaseId(int purchaseId) {
        return purchaseGoodsDao.queryByPurchaseId(purchaseId);
    }
}
