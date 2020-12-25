package com.gp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;

@TableName(resultMap = "resultMap")
public class Staff {
    @TableId(
            value = "staff_id",
            type = IdType.AUTO
    )
    private Integer id;
    @TableField("staff_name")
    private String name;
    @TableField("staff_password")
    private String password;
    @TableField("staff_department")
    private String department;
    @TableField("staff_portrait")
    private String portrait;
    @TableField(
            exist = false
    )
    private List<Role> roles;

    public Staff() {
    }

    public Staff(Integer id, String name, String password, String department, String portrait, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.department = department;
        this.portrait = portrait;
        this.roles = roles;
    }

    public String toString() {
        return "User{id=" + this.id + ", name='" + this.name + '\'' + ", password='" + this.password + '\'' + ", department='" + this.department + '\'' + ", portrait='" + this.portrait + '\'' + ", roles=" + this.roles + '}';
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
