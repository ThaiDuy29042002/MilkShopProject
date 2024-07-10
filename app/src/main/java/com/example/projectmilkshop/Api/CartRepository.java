package com.example.projectmilkshop.Api;

public class CartRepository {
    public static CartService getCartService(){
        return APIClient.getClient().create(CartService.class);
    }
}
