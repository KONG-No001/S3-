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
    UserDao userDao;

    @Override
    public List<User> listUser() {
        return userDao.selectList(null);
    }
}
