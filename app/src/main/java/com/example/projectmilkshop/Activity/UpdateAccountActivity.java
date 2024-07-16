package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.auth0.android.jwt.JWT;
import com.example.projectmilkshop.Api.AccountRepository;
import com.example.projectmilkshop.Api.AccountService;
import com.example.projectmilkshop.Domain.AuthResponse;
import com.example.projectmilkshop.Domain.UpdateAccountRequest;
import com.example.projectmilkshop.Interceptor.SessionManager;
import com.example.projectmilkshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccountActivity extends AppCompatActivity {
    private EditText edtUsername, edtPhone, edtAddress, edtDob, edtEmail, edtPassword;
    private Button btnSave, btnCancel;
    private UpdateAccountRequest updateAccountRequest;
    private SessionManager sessionManager;
    private AccountService accountService;
    private String jwtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_account);
        edtUsername = findViewById(R.id.edtUsername);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtDob = findViewById(R.id.edtDob);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        updateAccountRequest = new UpdateAccountRequest();
        sessionManager = new SessionManager(getApplicationContext());
        jwtToken = sessionManager.getJwtToken();
        accountService = AccountRepository.getAccountService();

        btnSave.setOnClickListener(v -> {
            updateAccout();
        });

        btnCancel.setOnClickListener(v -> {
            startActivity(new Intent(UpdateAccountActivity.this, ProfileActivity.class));
            finish();
        });

        decodeJwtToken(jwtToken);
    }

    private void updateAccout() {
        updateAccountRequest.setUserName(edtUsername.getText().toString());
        updateAccountRequest.setPhone(edtPhone.getText().toString());
        updateAccountRequest.setAddress(edtAddress.getText().toString());
        updateAccountRequest.setEmail(edtEmail.getText().toString());
        updateAccountRequest.setPassword(edtPassword.getText().toString());
        updateAccountRequest.setDob(edtDob.getText().toString());

        Call<AuthResponse> call = accountService.UpdateAccount(updateAccountRequest);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Toast.makeText(UpdateAccountActivity.this, "Update successfully", Toast.LENGTH_LONG).show();
                    String jwtToken = response.body().getAccess_token();
                    sessionManager.createLoginSession(jwtToken);
                    startActivity(new Intent(UpdateAccountActivity.this, ProfileActivity.class));
                    finish();
                } else {
                    Toast.makeText(UpdateAccountActivity.this, "Update fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void decodeJwtToken(String jwtToken) {
        try {
            JWT jwt = new JWT(jwtToken);

            String username = jwt.getClaim("username").asString();
            String address = jwt.getClaim("address").asString();
            String dob = jwt.getClaim("dob").asString();
            String phone = jwt.getClaim("phone").asString();
            String email = jwt.getClaim("email").asString();

            edtUsername.setText(username);
            edtAddress.setText(address);
            edtDob.setText(dob);
            edtPhone.setText(phone);
            edtEmail.setText(email);
            edtPassword.setText("1");
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
    }
}