package com.guhun.locatorapplication07.server;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.guhun.locatorapplication07.data.model.WifiSignalModel;

import java.util.ArrayList;
import java.util.List;

public class WifiManagerGH {
//    private WifiManager wifiManager;    // manager对象
//    private WifiInfo wifiInfo;          // wifi信息
    private List<ScanResult> wifiList;  // 网络列表，使用时用signalList代替
    private ArrayList<WifiSignalModel> signalList; //信号列表

    public WifiManagerGH(Context context){
        // 初始化信号信息
        WifiManager wifiManager = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.getConnectionInfo();
        wifiManager.startScan();
        wifiList = wifiManager.getScanResults();
    }

    /**
    * @Description: 初始化信号列表
    * @Param: [size]列表大小
    * @return: java.util.ArrayList<com.guhun.locatorapplication07.data.model.WifiSignalModel>
    * @Author: GuHun
    * @Date: 2021/3/14
    */
    public ArrayList<WifiSignalModel> initSignalList(int size) {
        signalList = new ArrayList<>();
        int index = 1;
        for (ScanResult item : wifiList){
            WifiSignalModel wifiSignal =
                    new WifiSignalModel(index,item.SSID,item.BSSID,item.level);
            index++;
            // 只存前num个
            if(signalList.size() < size){
                signalList.add(wifiSignal);
            }else {
                break;
            }
        }
        return signalList;
    }

    // 获取信号列表信息
    public ArrayList<WifiSignalModel> getSignalList() {
        return signalList;
    }
}
