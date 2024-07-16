package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Account;
import com.example.projectmilkshop.Domain.AuthRequest;
import com.example.projectmilkshop.Domain.AuthResponse;
import com.example.projectmilkshop.Domain.UpdateAccountRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountService {
    String LOGIN="login";
    String REGISTER="register";
    String UPDATEACCOUNT="update-account";

    @POST(LOGIN)
    Call<AuthResponse> Login(@Body AuthRequest account);

    @POST(REGISTER)
    Call<AuthResponse> Register(@Body Account account);

    @POST(UPDATEACCOUNT)
    Call<AuthResponse> UpdateAccount(@Body UpdateAccountRequest account);
}
