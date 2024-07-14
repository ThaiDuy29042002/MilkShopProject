package com.example.projectmilkshop.Api;

public class OrderRepository {
    public static OrderService getOrderService(String jwtToken){
        return APIClient.getClientWithAuth(jwtToken).create(OrderService.class);
    }
}
