package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gp.dao.UserDao;
import com.gp.service.UserService;
import com.gp.vo.Menu;
import com.gp.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> listUser(String searchType,String searchValue){

        QueryWrapper<User> queryWrapper =  new QueryWrapper<User>();

        queryWrapper.like(searchType,searchValue);

        System.out.println("true");

        queryWrapper.select(
                "user_id id",
                "user_name name",
                "user_department department",
                "user_portrait portrait");

        return userDao.selectList(queryWrapper);
    }

    @Override
    public Boolean updateUser(User user) {
        return userDao.updateById(user)>0;
    }

    @Override
    public Boolean addUser(User user) {
        user.setPassword("123456");
        System.out.println(user);
        return userDao.insert(user)>0;
//        return false;
    }

    @Override
    public Boolean deleteUser(int id) {
        return userDao.deleteById(id)>0;
    }
}
