package com.example.projectmilkshop.Domain;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable error);
}
