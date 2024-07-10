package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Cart;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartService {
    String CART="cart";

    @GET(CART)
    Call<Cart[]> GetCartOfAccount();

    @POST(CART)
    Call<Cart> AddProductToCart(@Body Cart cart);

    @DELETE(CART)
    Call<Cart> RemoveProductFromCart(@Body Cart cart);
}
