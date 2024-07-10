package com.example.projectmilkshop.Domain;

import java.util.Date;

public class Account {
    private int AccountId;
    private int RoleId;
    private String UserName;
    private String Phone;
    private String Address;
    private Date Dob;
    private String Email;
    private String Password;

    public Account(int accountId, int roleId, String userName, String phone, String address, Date dob, String email, String password) {
        AccountId = accountId;
        RoleId = roleId;
        UserName = userName;
        Phone = phone;
        Address = address;
        Dob = dob;
        Email = email;
        Password = password;
    }

    public Account(String userName, String phone, String address, Date dob, String email, String password) {
        UserName = userName;
        Phone = phone;
        Address = address;
        Dob = dob;
        Email = email;
        Password = password;
    }

    public Account(String email, String password) {
        Email = email;
        Password = password;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
