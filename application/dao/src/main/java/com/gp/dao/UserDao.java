package com.gp.dao;

import com.gp.vo.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
public interface UserDao {
    //根据id查用户
    User queryById(int id);

    public User queryUser(User user);

    public int addUser(User user);

    public User queryUserByName(User user);

    //成为商户
    int upStatus(int id);
}
