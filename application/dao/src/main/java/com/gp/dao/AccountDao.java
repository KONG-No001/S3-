package com.gp.dao;

import com.gp.vo.Account;
import com.gp.vo.DingDan;
import com.gp.vo.PurchaseVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
public interface AccountDao {
    //查所有
    List<Account> queryAll();

    //添加
    int add(Account account);

    List<Account> fenYe(Account account);
    int queryCount(Account account);

    List<Account> fenYe1(Account account);
    int queryCount1(Account account);

    List<Account> fenYe2(Account account);
    int queryCount2(Account account);


}
