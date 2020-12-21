package com.gp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName
public class Permission {
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "permission_name")
    private String name;
    @TableField(value = "permission_resources")
    private String resources;

    public Permission() {
    }

    public Permission(Integer id, String name, String resources) {
        this.id = id;
        this.name = name;
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resources='" + resources + '\'' +
                '}';
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

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
