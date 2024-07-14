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
import com.example.projectmilkshop.Domain.Account;
import com.example.projectmilkshop.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    AccountService accountService;
    private EditText edtName, edtEmail, edtPhone, edtAddress, edtPassword, edtCFPassword, edtDate;
    private TextView tvRegister, tvLogin;
    private final String REQUIRE = "Require";
    private final String REQUIRE1 = "Password and Confirm Password not match";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtDate = findViewById(R.id.edtDate);
        edtPassword = findViewById(R.id.edtPassword);
        edtCFPassword = findViewById(R.id.edtCFPassword);
        tvRegister = findViewById(R.id.tvRegister);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

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
        if (TextUtils.isEmpty(edtPhone.getText().toString())) {
            edtPhone.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(edtAddress.getText().toString())) {
            edtAddress.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(edtName.getText().toString())) {
            edtName.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(edtCFPassword.getText().toString())) {
            edtCFPassword.setError(REQUIRE);
            return false;
        }
        if (!edtPassword.getText().toString().equals(edtCFPassword.getText().toString())) {
            edtCFPassword.setError(REQUIRE1);
            return false;
        }
        return true;
    }

    private void signIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpForm() {
        checkInput();
        String Name = edtName.getText().toString();
        String Email = edtEmail.getText().toString();
        String Phone = edtPhone.getText().toString();
        String dateString = edtDate.getText().toString();
        String Address = edtAddress.getText().toString();
        String Password = edtPassword.getText().toString();

        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(dateString);
            Account register = new Account(Name, Phone, Address, date, Email, Password);
            Call<Account> call = accountService.Register(register);
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.body() != null){
                        Toast.makeText(RegisterActivity.this,"Register Successfully", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,"Login Fail", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e) {
            Log.d("Fix your information", e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvLogin) {
            signIn();
        } else if (id == R.id.tvRegister) {
            signUpForm();
        }
    }
}