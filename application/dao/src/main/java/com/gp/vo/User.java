package com.gp.vo;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private Integer status;
    private String phone;

    public User() {
    }

    public User(Integer id, String name, String password, String sex, Integer status, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.status = status;
        this.phone = phone;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }
}
