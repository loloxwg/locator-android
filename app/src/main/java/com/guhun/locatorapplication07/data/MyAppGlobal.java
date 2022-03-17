package com.guhun.locatorapplication07.data;

import android.app.Application;

import com.guhun.locatorapplication07.data.model.UserModel;

public class MyAppGlobal extends Application {

    private String serverUrl;
    private UserModel userModel;
    private String userId;
    private String right;
    private String imgUrl;
    public final int FINDSignalSize = 4; // 指纹匹配时采集尺寸
    public final int INSERTSignalSize = 10; // 指纹采集时上传尺寸
    public final int REFRESHTIME = 10; // 扫描时间间隔(秒)
    public final int ERROR_RATE = 10; // 误差率，单位%
    @Override
    public void onCreate()
    {
        super.onCreate();
        imgUrl = "https://wifimap-1304806518.cos.ap-shanghai.myqcloud.com/"; // 图片服务器地址
        serverUrl = "http://124.222.47.219:9100/locator_server"; // 服务器地址
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
