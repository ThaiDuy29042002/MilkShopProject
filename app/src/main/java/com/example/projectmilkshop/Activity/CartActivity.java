package com.example.projectmilkshop.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmilkshop.Adapter.CartAdapter;
import com.example.projectmilkshop.Api.CartRepository;
import com.example.projectmilkshop.Api.CartService;
import com.example.projectmilkshop.Api.ProductService;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Cart> cartItems = new ArrayList<>();

    private TextView tvTotal;
    private ConstraintLayout btnCheckout;
    private ScrollView scrollView;
    CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartService = CartRepository.getCartService();
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        recyclerView = findViewById(R.id.viewCart);
        scrollView = findViewById(R.id.srcollViewCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(cartItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);

        fetchCartItems();
        bottomNavigation();
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    private void fetchCartItems() {
        Call<Cart[]> call = cartService.GetCartOfAccount();
        call.enqueue(new Callback<Cart[]>() {
            @Override
            public void onResponse(Call<Cart[]> call, Response<Cart[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cartItems.clear();
                    cartItems.addAll(Arrays.asList(response.body()));
                    adapter.notifyDataSetChanged();
                    updateTotalPrice();
                } else {
                    Log.e(TAG, "Failed to fetch cart items: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Cart[]> call, Throwable t) {
                Log.e(TAG, "Failed to fetch cart items", t);
            }
        });
    }


    private void updateTotalPrice() {
        double total = 0;
        for (Cart cart : cartItems) {
            total += cart.getUnitPrice() * cart.getQuantity();
        }
        tvTotal.setText(String.valueOf(total));
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
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ProductActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ChatboxActivity.class));
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MapActivity.class));
            }
        });

    }
}