package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Account;
import com.example.projectmilkshop.Domain.Order;
import com.example.projectmilkshop.Domain.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {
    String ORDER="order";

    @GET("checkout")
    Call<OrderResponse> CreateOrder();

}
