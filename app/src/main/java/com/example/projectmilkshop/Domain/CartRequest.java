package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class CartRequest {
    @SerializedName("productId")
    private int productId;
    @SerializedName("accountId")
    private int accountId;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("status")
    private int status;

    public CartRequest(int productId, int accountId, int quantity, int status) {
        this.productId = productId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

