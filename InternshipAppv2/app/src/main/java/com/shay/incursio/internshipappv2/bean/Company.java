package com.shay.incursio.internshipappv2.bean;

/**
 * Created by incursio on 7/22/2017.
 */

public class Company {


    private static String companyId;
    private static String username;
    private static String password;
    private static String name;
    private static String address;
    private static String postcode;
    private static String state;
    private static String contactNumber;
    private static String email;

    public static String getCompanyId() {
        return companyId;
    }

    public static void setCompanyId(String companyId) {
        Company.companyId = companyId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Company.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Company.password = password;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Company.name = name;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Company.address = address;
    }

    public static String getContactNumber() {
        return contactNumber;
    }

    public static void setContactNumber(String contactNumber) {
        Company.contactNumber = contactNumber;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Company.email = email;
    }

    public static String getPostcode() {
        return postcode;
    }

    public static void setPostcode(String postcode) {
        Company.postcode = postcode;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        Company.state = state;
    }
}
