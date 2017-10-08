package com.shay.incursio.internshipappv2.bean;

/**
 * Created by shay on 7/22/2017.
 */

public class Student {
    private static String userId;
    private static String username;
    private static String password;
    private static String usertype;
    private static String studentId;
    private static String icNo;
    private static String name;
    private static String email;
    private static String telNo;
    private static String address;
    private static String status;
    private static String batchId;
    private static String course;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Student.userId = userId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Student.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Student.password = password;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        Student.usertype = usertype;
    }

    public static String getStudentId() {
        return studentId;
    }

    public static void setStudentId(String studentId) {
        Student.studentId = studentId;
    }

    public static String getIcNo() {
        return icNo;
    }

    public static void setIcNo(String icNo) {
        Student.icNo = icNo;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Student.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Student.email = email;
    }

    public static String getTelNo() {
        return telNo;
    }

    public static void setTelNo(String telNo) {
        Student.telNo = telNo;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Student.address = address;
    }

    public static String getCourse() {
        return course;
    }

    public static void setCourse(String course) {
        Student.course = course;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Student.status = status;
    }

    public static String getBatchId() {
        return batchId;
    }

    public static void setBatchId(String batchId) {
        Student.batchId = batchId;
    }
}
