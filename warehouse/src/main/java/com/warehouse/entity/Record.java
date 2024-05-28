package com.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author promefire
 * @date 2024/5/6 8:39
 * description: 用户取出放进记录实体
 */
public class Record {
    private int id;

    private int goods;

    @TableField("UserId")
    private int userId;

    @TableField("admin_id")
    private int adminId;

    private int count;

    @TableField("createtime")
    private String createTime;

    private String remark;

    @TableField(exist = false)
    private String Action;

    @TableField(exist = false)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", goods=" + goods +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", count=" + count +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
