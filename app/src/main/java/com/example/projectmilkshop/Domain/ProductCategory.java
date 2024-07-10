package com.example.projectmilkshop.Domain;

public class ProductCategory {

    private int ProductCategoryId;
    private String CategoryName;

    public ProductCategory(int productCategoryId, String categoryName) {
        ProductCategoryId = productCategoryId;
        CategoryName = categoryName;
    }

    public int getProductCategoryId() {
        return ProductCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        ProductCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
