package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.Staff;
import java.util.Set;

public interface StaffDao extends BaseMapper<Staff> {
    Staff getStaff(String var1);

    Set<String> listRoles(String var1);

    Set<String> listPermissions(String var1);
}
