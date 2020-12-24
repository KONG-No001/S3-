package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gp.dao.StaffDao;
import com.gp.dao.StaffRoleRelationDao;
import com.gp.service.StaffService;
import com.gp.vo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDao staffDao;

    @Autowired
    StaffRoleRelationDao staffRoleRelationDao;

    @Override
    public List<Staff> listStaff(String searchType, String searchValue){

        QueryWrapper<Staff> queryWrapper =  new QueryWrapper<Staff>();

        queryWrapper.like(searchType,searchValue);

        System.out.println("true");

        queryWrapper.select(
                "staff_id",
                "staff_name",
                "staff_department",
                "staff_portrait");


        return staffDao.selectList(queryWrapper);

//        System.out.println(staffRoleRelationDao.getRolesByStaffId(1));
//        return null;
    }

    @Override
    public Boolean updateStaff(Staff staff) {
        return staffDao.updateById(staff)>0;
    }

    @Override
    public Boolean addStaff(Staff staff) {
        staff.setPassword("123456");
        System.out.println(staff);
        return staffDao.insert(staff)>0;
//        return false;
    }

    @Override
    public Boolean deleteStaff(int id) {
        return staffDao.deleteById(id)>0;
    }

    @Override
    public Boolean updateRoleRelation(int staffId, List<Integer> roleIds) {
        staffRoleRelationDao.closeRoleRelation(staffId);
        if(roleIds.size()>0){
            return staffRoleRelationDao.updateRoleRelation(staffId,roleIds)>0;
        }else {
            return true;
        }

//        System.out.println(staffId);
//        System.out.println(roleIds);
//        return false;
    }


}
