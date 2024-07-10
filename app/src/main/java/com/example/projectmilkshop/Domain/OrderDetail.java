package com.example.projectmilkshop.Domain;

public class OrderDetail {

    private int OrderDetailId;

    private int OrderId;

    private int ProductId;

    private int OrderQuantity;

    private double ProductPrice;

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
