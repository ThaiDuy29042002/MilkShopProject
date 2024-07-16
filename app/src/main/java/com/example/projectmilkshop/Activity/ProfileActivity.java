package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.auth0.android.jwt.JWT;
import com.example.projectmilkshop.Interceptor.SessionManager;
import com.example.projectmilkshop.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView edtUsername;
    private TextView edtAddress;
    private TextView edtDob;
    private TextView edtPhone;
    private TextView edtEmail;
    private Button btnUpdate;
    private Button btnMyOrder;
    private String jwtToken;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        edtUsername = findViewById(R.id.edtUsername);
        edtAddress = findViewById(R.id.edtAddress);
        edtDob = findViewById(R.id.edtDob);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnMyOrder = findViewById(R.id.btnMyOrder);
        sessionManager = new SessionManager(getApplicationContext());
        jwtToken = sessionManager.getJwtToken();

        btnUpdate.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, UpdateAccountActivity.class));
            finish();
        });

        btnMyOrder.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, MyOrderActivity.class));
            finish();
        });

        decodeJwtToken(jwtToken);
        bottomNavigation();
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
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
    }

    private void bottomNavigation() {
        LinearLayout homebtn = findViewById(R.id.homeBtn);
        LinearLayout profilebtn = findViewById(R.id.profilebtn);
        LinearLayout cartbtn = findViewById(R.id.cartbtn);
        LinearLayout supportbtn = findViewById(R.id.supportbtn);
        LinearLayout mapbtn = findViewById(R.id.btnMap);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProductActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ChatboxActivity.class));
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MapActivity.class));
            }
        });
    }
}