package com.zhang.administrator.thermal.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MyUser extends BmobUser {
    private String nickName;
    private int age;
    private String address;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
