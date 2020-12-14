package com.gp.service;

import com.gp.vo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> listUser(String searchType,String searchValue);
    Boolean updateUser(User user);
    Boolean addUser(User user);
    Boolean deleteUser(int id);

}
