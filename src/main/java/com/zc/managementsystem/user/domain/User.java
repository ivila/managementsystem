package com.zc.managementsystem.user.domain;

public class User {
    private long id;
    private String userName;
    private String email;
    private String password;
    private long createTime;
    private long modifyTime;

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

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getCreateTime() {
        return createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
