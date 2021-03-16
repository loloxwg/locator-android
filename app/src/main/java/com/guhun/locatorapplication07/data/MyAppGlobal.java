package com.guhun.locatorapplication07.data;

import android.app.Application;

import com.guhun.locatorapplication07.data.model.UserModel;

public class MyAppGlobal extends Application {

    private String serverUrl;
    private UserModel userModel;
    private String userId;
    private String right;
    private String imgUrl;
    @Override
    public void onCreate()
    {
        super.onCreate();
        imgUrl="https://wifimap-1304806518.cos.ap-shanghai.myqcloud.com/";
        setServerUrl("http://121.4.217.63:8080/locator_server"); // 初始化全局变量
    }
    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
