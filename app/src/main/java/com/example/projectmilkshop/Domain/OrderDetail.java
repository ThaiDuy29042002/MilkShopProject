package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("orderDetailId")
    private int OrderDetailId;
    @SerializedName("orderDetailId")
    private int OrderId;
    @SerializedName("productId")
    private int ProductId;
    @SerializedName("orderQuantity")
    private int OrderQuantity;
    @SerializedName("productPrice")
    private double ProductPrice;
    @SerializedName("status")
    private int Status;

    public OrderDetail(int orderDetailId, int orderId, int productId, int orderQuantity, double productPrice, int status) {
        OrderDetailId = orderDetailId;
        OrderId = orderId;
        ProductId = productId;
        OrderQuantity = orderQuantity;
        ProductPrice = productPrice;
        Status = status;
    }

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getOrderQuantity() {
        return OrderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        OrderQuantity = orderQuantity;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
