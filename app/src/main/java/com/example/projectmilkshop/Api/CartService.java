package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.CartRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface CartService {
    String CART="cart";

    @GET(CART)
    Call<Cart[]> GetCartOfAccount();

    @POST(CART)
    Call<Void> AddProductToCart(@Body CartRequest cart);

    @DELETE(CART)
    Call<Void> RemoveProductFromCart(@Query("cartId") int cartId);

    @PUT(CART)
    Call<Void> UpdateCartItem(@Query("cartId") int cartId, @Body CartRequest cart);
}
