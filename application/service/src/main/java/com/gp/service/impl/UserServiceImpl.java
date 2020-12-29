package com.gp.service.impl;

import com.gp.dao.UserDao;
import com.gp.service.UserService;
import com.gp.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;


    @Override
    public User queryUser(User user) {

        return dao.queryUser(user);
    }

    @Override
    public User queryUserByName(User user) {
        return dao.queryUserByName(user);
    }

    @Override
    public int addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public int upStatus(int id) {
        return dao.upStatus(id);
    }
}
