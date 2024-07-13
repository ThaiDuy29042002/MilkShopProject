package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("cartId")
    private int CartId;
    @SerializedName("productId")
    private int ProductId;
    @SerializedName("accountId")
    private int AccountId;
    @SerializedName("quantity")
    private int Quantity;
    @SerializedName("product")
    private Product Product;

    public Cart(int cartId, int productId, int accountId, int quantity) {
        CartId = cartId;
        ProductId = productId;
        AccountId = accountId;
        Quantity = quantity;
    }

    public Cart(int cartId, int productId, int accountId, int quantity, com.example.projectmilkshop.Domain.Product product) {
        CartId = cartId;
        ProductId = productId;
        AccountId = accountId;
        Quantity = quantity;
        Product = product;
    }

    public com.example.projectmilkshop.Domain.Product getProduct() {
        return Product;
    }

    public void setProduct(com.example.projectmilkshop.Domain.Product product) {
        Product = product;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
