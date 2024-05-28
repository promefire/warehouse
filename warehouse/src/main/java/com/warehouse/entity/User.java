package com.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;


public class User {
    private int id;
    private String no;
    private String name;
    private String password;
    private int sex;

    @TableField("role_id")
    private int roleId;
    private String phone;

    @TableField("isValid")
    private String isvalid;
    private int age;


//    表示这个字段在数据库表中不存在
    @TableField(exist = false)
    private List<Record> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", roleId=" + roleId +
                ", phone='" + phone + '\'' +
                ", isValid='" + isvalid + '\'' +
                ", age=" + age +
                '}';
    }
}
