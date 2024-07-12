package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectmilkshop.Api.AccountRepository;
import com.example.projectmilkshop.Api.AccountService;
import com.example.projectmilkshop.Domain.AuthRequest;
import com.example.projectmilkshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail, edtPassword;
    private TextView tvRegister, tvLogin, tvForgotPassword;
    private final String REQUIRE = "Require";
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        accountService = AccountRepository.getAccountService();
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            edtPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void signIn() {
        checkInput();
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        AuthRequest login = new AuthRequest(email,pass);
        try{
            Call<AuthRequest> call = accountService.Login(login);
            call.enqueue(new Callback<AuthRequest>() {
                @Override
                public void onResponse(Call<AuthRequest> call, Response<AuthRequest> response) {
                    if(response.body() != null){
                        Toast.makeText(LoginActivity.this,"Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<AuthRequest> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Login Fail", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }catch (Exception e) {
            Log.d("Fix your information", e.getMessage());
        }

    }

    private void signUpForm() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvLogin) {
            signIn();
        } else if (id == R.id.tvRegister) {
            signUpForm();
        }
        else if (id == R.id.tvForgotPassword) {
            signUpForm();
        }
    }
}