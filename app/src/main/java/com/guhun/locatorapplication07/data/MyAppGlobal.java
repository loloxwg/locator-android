package com.guhun.locatorapplication07.data;

import android.app.Application;

import com.guhun.locatorapplication07.data.model.UserModel;

public class MyAppGlobal extends Application {

    private String serverUrl;
    private UserModel userModel;
    private String userId;
    @Override
    public void onCreate()
    {
        super.onCreate();
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
}
