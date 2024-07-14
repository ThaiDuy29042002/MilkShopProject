package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("cartId")
    private int CartId;
    @SerializedName("productId")
    private int ProductId;
    @SerializedName("accountId")
    private int AccountId;
    @SerializedName("unitPrice")
    private double UnitPrice;
    @SerializedName("quantity")
    private int Quantity;
    @SerializedName("product")
    private Product Product;

    private String pic = "milk_pint";

    public Cart(int cartId, int productId, int accountId, int quantity, double unitPrice, Product product) {
        CartId = cartId;
        ProductId = productId;
        AccountId = accountId;
        Quantity = quantity;
        UnitPrice = unitPrice;
        Product = product;
    }


    public Cart(int cartId, int productId, int accountId, int quantity, Product product) {
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

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
