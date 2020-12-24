package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffRoleRelationDao extends BaseMapper<Object> {

    List<Role> getRolesByStaffId(int id);

    int updateRoleRelation(@Param("staffId") int staffId, @Param("roleIds")List<Integer> roleIds);
    int closeRoleRelation(int staffId);

}
