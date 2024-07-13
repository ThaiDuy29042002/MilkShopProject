package com.example.projectmilkshop.Interceptor;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String jwtToken;

    public AuthInterceptor(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        if(jwtToken != null) {
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + jwtToken)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
