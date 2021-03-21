package com.guhun.locatorapplication07.data.model;

/**
 * @program: LocatorApplication07
 * @description:WifiSignalModel
 * @author: GuHun
 * @create: 2021-03-14 10:53
 **/
public class WifiSignalModel {
    private int index;
    private int signalId;
    private int signalWifiId;
    private String signalName;
    private String signalMac;
    private int signalPower;


    public WifiSignalModel(){}
    public WifiSignalModel(int index, int signalWifiId, String signalName, String signalMac, int signalPower){
        this.index = index;
        this.signalWifiId = signalWifiId;
        this.signalName = signalName;
        this.signalMac = signalMac;
        this.signalPower = signalPower;
    }

    @Override
    public String toString() {
        return "WifiSignalModel{" +
                "signalId=" + signalId +
                ", signalWifiId=" + signalWifiId +
                ", signalName='" + signalName + '\'' +
                ", signalMac='" + signalMac + '\'' +
                ", signalPower=" + signalPower +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSignalId() {
        return signalId;
    }

    public void setSignalId(int signalId) {
        this.signalId = signalId;
    }

    public int getSignalWifiId() {
        return signalWifiId;
    }

    public void setSignalWifiId(int signalWifiId) {
        this.signalWifiId = signalWifiId;
    }

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public String getSignalMac() {
        return signalMac;
    }

    public void setSignalMac(String signalMac) {
        this.signalMac = signalMac;
    }

    public int getSignalPower() {
        return signalPower;
    }

    public void setSignalPower(int signalPower) {
        this.signalPower = signalPower;
    }
}
