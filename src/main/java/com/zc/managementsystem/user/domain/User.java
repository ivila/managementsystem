package com.zc.managementsystem.user.domain;

public class User {
    private long id;
    private String userName;
    private String password;
    private int createTime;
    private int modifyTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(int modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return this.userName;
    }
    public String getPassword() {
        return password;
    }

    public int getCreateTime() {
        return createTime;
    }

    public int getModifyTime() {
        return modifyTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
