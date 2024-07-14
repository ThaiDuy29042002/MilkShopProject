package com.example.projectmilkshop.Api;

public class CartRepository {
    public static CartService getCartService(String jwtToken){
        return APIClient.getClientWithAuth(jwtToken).create(CartService.class);
    }
}
