package com.gp.dao;

import com.gp.vo.Account;

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

    int querycount();
}
