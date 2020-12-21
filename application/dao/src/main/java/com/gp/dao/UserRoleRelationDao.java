package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleRelationDao extends BaseMapper<Object> {

    List<Role> getRolesByUserId(int id);

    int updateRoleRelation(@Param("userId") int userId, @Param("roleIds")List<Integer> roleIds);
    int closeRoleRelation(int userId);

}
