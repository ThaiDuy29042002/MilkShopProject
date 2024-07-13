package com.example.projectmilkshop.Domain;

public class Cart {

    private int CartId;

    private int ProductId;

    private double UnitPrice;
    private int AccountId;

    private int Quantity;
    private String pic = "milk_pint";


    public Cart(int cartId, int productId, int accountId, int quantity) {
        CartId = cartId;
        ProductId = productId;
        AccountId = accountId;
        Quantity = quantity;
    }

    public Cart(int cartId, int productId, double unitPrice, int accountId, int quantity) {
        CartId = cartId;
        ProductId = productId;
        UnitPrice = unitPrice;
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
