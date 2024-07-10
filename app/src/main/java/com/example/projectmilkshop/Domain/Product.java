package com.example.projectmilkshop.Domain;

public class Product {
    private int ProductId;
    private String ProductName;
    private String ProductDescription;
    private String pic = "milk";

    private double Capacity;
    private double ProductPrice;

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
