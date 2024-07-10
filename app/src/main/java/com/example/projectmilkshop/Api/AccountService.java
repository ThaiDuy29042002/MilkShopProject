package com.example.projectmilkshop.Api;

import com.example.projectmilkshop.Domain.Account;
import com.example.projectmilkshop.Domain.AuthRequest;

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
    Call<AuthRequest> Login(@Body AuthRequest account);

    @POST(REGISTER)
    Call<Account> Register(@Body Account account);

    @POST(UPDATEACCOUNT)
    Call<Account> UpdateAccount(@Body Account account);
}
