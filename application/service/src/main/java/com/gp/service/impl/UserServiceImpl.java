package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gp.dao.UserDao;
import com.gp.dao.UserRoleRelationDao;
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

    @Autowired
    UserRoleRelationDao userRoleRelationDao;

    @Override
    public List<User> listUser(String searchType,String searchValue){

        QueryWrapper<User> queryWrapper =  new QueryWrapper<User>();

        queryWrapper.like(searchType,searchValue);

        System.out.println("true");

        queryWrapper.select(
                "user_id",
                "user_name",
                "user_department",
                "user_portrait");


        return userDao.selectList(queryWrapper);

//        System.out.println(userRoleRelationDao.getRolesByUserId(1));
//        return null;
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

    @Override
    public Boolean updateRoleRelation(int userId, List<Integer> roleIds) {
        userRoleRelationDao.closeRoleRelation(userId);
        if(roleIds.size()>0){
            return userRoleRelationDao.updateRoleRelation(userId,roleIds)>0;
        }else {
            return true;
        }

//        System.out.println(userId);
//        System.out.println(roleIds);
//        return false;
    }


}
