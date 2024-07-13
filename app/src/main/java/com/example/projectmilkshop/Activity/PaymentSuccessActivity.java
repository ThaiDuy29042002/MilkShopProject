package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectmilkshop.R;

public class PaymentSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_success);

        Uri data = getIntent().getData();
        if (data != null) {
            String status = data.getQueryParameter("status");
            String orderId = data.getQueryParameter("orderId");

            TextView tvMessage = findViewById(R.id.tvMessage);
            tvMessage.setText("Thanh toán thành công!\nMã đơn hàng: " + orderId);
        }

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}