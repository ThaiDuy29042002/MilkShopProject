package com.example.projectmilkshop.Domain;

public class AuthResponse {
    private String access_token;

    public AuthResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}