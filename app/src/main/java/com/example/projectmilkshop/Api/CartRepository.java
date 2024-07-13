package com.example.projectmilkshop.Api;

public class CartRepository {
    public static CartService getCartService(String jwtToken){
        return APIClient.getClient(jwtToken).create(CartService.class);
    }
}
