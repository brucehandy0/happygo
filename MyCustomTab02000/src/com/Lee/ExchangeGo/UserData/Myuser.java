package com.Lee.ExchangeGo.UserData;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2015/7/26.
 */
public class Myuser extends BmobUser {
    private String nickname;
    private String Sex;
    private String address;
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
