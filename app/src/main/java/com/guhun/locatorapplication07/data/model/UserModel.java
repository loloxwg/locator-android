package com.guhun.locatorapplication07.data.model;

import android.net.Uri;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class UserModel {
    @JSONField(name = "userId")
    private String userId;
    @JSONField(name = "userEmpId")
    private String userEmpId;
    @JSONField(name = "userRight")
    private String userRight;
    @JSONField(name = "empId")
    private String empId;
    @JSONField(name = "empName")
    private String empName;
    @JSONField(name = "empSex")
    private String empSex;
    @JSONField(name = "empAge")
    private String empAge;
    @JSONField(name = "empTel")
    private String empTel;
    @JSONField(name = "empJob")
    private String empJob;
    @JSONField(name = "empHobby")
    private String empHobby;
    @JSONField(name = "empMsg")
    private String empMsg;

    public UserModel() {

    }

    public UserModel(String userId, String userEmpId, String userRight, String empId, String empName, String empSex,
                     String empAge, String empTel, String empJob, String empHobby, String empMsg) {
        this.userId = userId;
        this.userEmpId = userEmpId;
        this.userRight = userRight;
        this.empId = empId;
        this.empName = empName;
        this.empSex = empSex;
        this.empAge = empAge;
        this.empTel = empTel;
        this.empJob = empJob;
        this.empHobby = empHobby;
        this.empMsg = empMsg;
    }

    public String toParams() {
//        return
//                (userId==""?"":"userId=" +Uri.encode(userId)+ '&') +
//                (userEmpId==""?"":"userEmpId=" +userEmpId+ '&') +
//                (userRight==""?"":"userRight=" +userRight+ '&') +
//                (empId==""?"":"empId=" +empId+ '&') +
//                (empName==""?"":"empName=" +empName+ '&') +
//                (empSex==""?"":"empSex=" +empSex+ '&') +
//                (empAge==""?"":"empAge=" +empAge+ '&') +
//                (empTel==""?"":"empTel=" +empTel+ '&') +
//                (empJob==""?"":"empJob=" +empJob+ '&') +
//                (empHobby==""?"":"empHobby=" +empHobby+ '&') +
//                (empMsg==""?"":"empMsg=" +empMsg+ '&');
        return
                (userId==""?"":"userId=" +Uri.encode(userId)+ '&') +
                (userEmpId==""?"":"userEmpId=" +Uri.encode(userEmpId)+ '&') +
                (userRight==""?"":"userRight=" +Uri.encode(userRight)+ '&') +
                (empId==""?"":"empId=" +Uri.encode(empId)+ '&') +
                (empName==""?"":"empName=" +Uri.encode(empName)+ '&') +
                (empSex==""?"":"empSex=" +Uri.encode(empSex)+ '&') +
                (empAge==""?"":"empAge=" +Uri.encode(empAge)+ '&') +
                (empTel==""?"":"empTel=" +Uri.encode(empTel)+ '&') +
                (empJob==""?"":"empJob=" +Uri.encode(empJob)+ '&') +
                (empHobby==""?"":"empHobby=" +Uri.encode(empHobby)+ '&') +
                (empMsg==""?"":"empMsg=" +Uri.encode(empMsg)+ '&');



    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmpId() {
        return userEmpId;
    }

    public void setUserEmpId(String userEmpId) {
        this.userEmpId = userEmpId;
    }

    public String getUserRight() {
        return userRight;
    }

    public void setUserRight(String userRight) {
        this.userRight = userRight;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpHobby() {
        return empHobby;
    }

    public void setEmpHobby(String empHobby) {
        this.empHobby = empHobby;
    }

    public String getEmpMsg() {
        return empMsg;
    }

    public void setEmpMsg(String empMsg) {
        this.empMsg = empMsg;
    }
}
