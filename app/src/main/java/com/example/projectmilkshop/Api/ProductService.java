package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    String PRODUCT = "product";
    String PRODUCTS = "products";

    @GET(PRODUCTS)
    Call<Product[]> GetAllProducts();
    @GET(PRODUCT + "/{productId}")
    Call<Product> GetProductById(@Path("productId") Object productId);

}
