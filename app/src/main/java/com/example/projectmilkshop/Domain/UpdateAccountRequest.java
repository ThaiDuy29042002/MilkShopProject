package com.example.projectmilkshop.Domain;

public class UpdateAccountRequest {
    private String userName;
    private String phone;
    private String address;
    private String dob;
    private String email;
    private String password;

    public UpdateAccountRequest() {
    }

    public UpdateAccountRequest(String userName, String phone, String address, String dob, String email, String password) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
