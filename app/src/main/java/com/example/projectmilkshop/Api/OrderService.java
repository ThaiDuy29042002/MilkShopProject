package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Account;
import com.example.projectmilkshop.Domain.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {
    String LOGIN="login";
    String ORDER="order";
    String UPDATEACCOUNT="update-account";

    @GET(ORDER)
    Call<Order> CreateOrder();


}
