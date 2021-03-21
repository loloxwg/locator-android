package com.guhun.locatorapplication07.server;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.data.model.WifiSignalModel;

import java.util.ArrayList;

public class WifiService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private WifiManagerGH wifiManagerGH;
    private MyAppGlobal global;
    // 循环调用
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            // 发送定位信息
            if(global.getUserId()!=null){
                wifiManagerGH = new WifiManagerGH(getApplicationContext());
                wifiManagerGH.initSignalList(global.FINDSignalSize,0);
                ArrayList<WifiSignalModel> signalList = wifiManagerGH.getSignalList();


                // 循环调用
                handler.postDelayed(this,10*1000);
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        handler.post(task);
    }
}
