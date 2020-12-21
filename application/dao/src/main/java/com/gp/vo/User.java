package com.gp.vo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName(resultMap = "resultMap")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_name")
    private String name;
    @TableField(value = "user_password")
    private String password;
    @TableField(value = "user_department")
    private String department;
    @TableField(value = "user_portrait")
    private String portrait;

    @TableField(exist=false)
    private List<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String password, String department, String portrait, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.department = department;
        this.portrait = portrait;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", portrait='" + portrait + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
