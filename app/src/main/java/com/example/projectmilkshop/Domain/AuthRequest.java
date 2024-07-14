package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {
    @SerializedName("email")

    public String Email;
    @SerializedName("password")

    public String Password;

    public AuthRequest(String email, String password) {
        Email = email;
        Password = password;
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
