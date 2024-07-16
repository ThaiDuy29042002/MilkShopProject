package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("orderId")
    private int OrderId;
    @SerializedName("accountId")
    private int AccountId;
    @SerializedName("totalPrice")
    private double TotalPrice;
    @SerializedName("status")
    private int Status;
    @SerializedName("orderDate")
    private String OrderDate;

    public Order(int orderId, int accountId, double totalPrice, int status, String orderDate) {
        OrderId = orderId;
        AccountId = accountId;
        TotalPrice = totalPrice;
        Status = status;
        OrderDate = orderDate;
    }


    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
