package com.example.projectmilkshop.Domain;

public class Cart {

    private int CartId;

    private int ProductId;

    private int AccountId;

    private int Quantity;

    public Cart(int cartId, int productId, int accountId, int quantity) {
        CartId = cartId;
        ProductId = productId;
        AccountId = accountId;
        Quantity = quantity;
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
