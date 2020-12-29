package com.gp.service;

import com.gp.vo.User;


public interface UserService {

    public User queryUser(User user);

    public User queryUserByName(User user);

    public int addUser(User user);

    //成为商户
    int upStatus(int id);

}
