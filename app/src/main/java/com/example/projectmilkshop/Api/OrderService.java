package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.VnpayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {
    String ORDER="order";

    @POST(ORDER)
    Call<VnpayResponse> CreateOrder();

}
