package com.gp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName
public class Role {
    @TableId(value = "role_id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "role_name")
    private String name;
    @TableField(value = "role_type")
    private String type;
    @TableField(value = "role_remark")
    private String remark;

    public Role() {
    }

    public Role(Integer id, String name, String type, String remark) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
