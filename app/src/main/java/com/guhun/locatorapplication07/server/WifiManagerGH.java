package com.guhun.locatorapplication07.server;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

public class WifiManagerGH {
    private WifiManager wifiManager;//manager对象
    private WifiInfo wifiInfo;//wifi信息
    private List<ScanResult> wifiList;//网络列表
    public WifiManagerGH(Context context){
        wifiManager = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        wifiManager.startScan();
        wifiList = wifiManager.getScanResults();
    }

}
