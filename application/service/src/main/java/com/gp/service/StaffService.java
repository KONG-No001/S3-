package com.gp.service;

import com.gp.vo.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> listStaff(String searchType, String searchValue);
    Boolean updateStaff(Staff staff);
    Boolean addStaff(Staff staff);
    Boolean deleteStaff(int id);

    Boolean updateRoleRelation(int staffId, List<Integer> roleIds);

}
