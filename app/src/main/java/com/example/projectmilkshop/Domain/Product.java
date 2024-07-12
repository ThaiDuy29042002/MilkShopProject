package com.example.projectmilkshop.Domain;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productId")
    private int ProductId;
    @SerializedName("productName")
    private String ProductName;
    @SerializedName("productDescription")
    private String ProductDescription;
    @SerializedName("capacity")
    private double Capacity;
    @SerializedName("productPrice")
    private double ProductPrice;
    private String pic = "milk";

    public Product(int productId, String productName, String productDescription, double capacity, double productPrice) {
        ProductId = productId;
        ProductName = productName;
        ProductDescription = productDescription;
        Capacity = capacity;
        ProductPrice = productPrice;
    }

    public Product(String productName, double capacity, double productPrice) {
        ProductName = productName;
        Capacity = capacity;
        ProductPrice = productPrice;
    }

    public Product(int productId, String productName, double capacity, double productPrice) {
        ProductId = productId;
        ProductName = productName;
        Capacity = capacity;
        ProductPrice = productPrice;
    }

    public Product(String productName, String productDescription, String pic, double capacity, double productPrice) {
        ProductName = productName;
        ProductDescription = productDescription;
        this.pic = pic;
        Capacity = capacity;
        ProductPrice = productPrice;
    }

    public Product(int productId, String productName, String productDescription, String pic, double capacity, double productPrice) {
        ProductId = productId;
        ProductName = productName;
        ProductDescription = productDescription;
        this.pic = pic;
        Capacity = capacity;
        ProductPrice = productPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public double getCapacity() {
        return Capacity;
    }

    public void setCapacity(double capacity) {
        Capacity = capacity;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }
}
