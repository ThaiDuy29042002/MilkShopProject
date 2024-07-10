package com.example.projectmilkshop.Domain;

public class Order {
    public int OrderId;
    public int AccountId;
    public double TotalPrice;
    public int Status;

    public Order(int orderId, int accountId, double totalPrice, int status) {
        OrderId = orderId;
        AccountId = accountId;
        TotalPrice = totalPrice;
        Status = status;
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
