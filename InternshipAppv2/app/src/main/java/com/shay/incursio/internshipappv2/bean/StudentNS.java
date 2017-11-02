package com.shay.incursio.internshipappv2.bean;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by User on 08/10/2017.
 */

public class StudentNS {
    private  String userId;
    private  String username;
    private  String password;
    private  String usertype;
    private  String studentId;
    private  String icNo;
    private  String name;
    private  String email;
    private  String telNo;
    private  String address;
    private  String status;
    private  String batchId;
    private  String course;
    private boolean box;
    private ArrayList<ApplicationStatusStudent> appStats;

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<ApplicationStatusStudent> getAppStats() {
        return appStats;
    }

    public void setAppStats(ArrayList<ApplicationStatusStudent> appStats) {
        this.appStats = appStats;
    }
}
