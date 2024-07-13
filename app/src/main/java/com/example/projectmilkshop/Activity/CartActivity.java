package com.example.projectmilkshop.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.Interceptor.SessionManager;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private TextView tvTotal;
    private ConstraintLayout btnCheckout;
    private ScrollView scrollView;

    private SessionManager sessionManager;
    private CartService cartService;
    private String jwtToken;

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

        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        recyclerView = findViewById(R.id.viewCart);
        scrollView = findViewById(R.id.srcollViewCart);

        sessionManager = new SessionManager(getApplicationContext());
        jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXIwMUBnbWFpbC5jb20iLCJyb2xlIjoiMiIsImFjY291bnRJZCI6IjEiLCJuYmYiOjE3MjA4NDI4NDUsImV4cCI6MTcyMDg0NjQ0NSwiaWF0IjoxNzIwODQyODQ1LCJpc3MiOiJJc3N1ZXIiLCJhdWQiOiJBdWRpZW5jZSJ9.smuFiJ7WlcLOIgCtH48KiGhd3bHDueOuQPiODsUUnt4";
                //sessionManager.getJwtToken();
        cartService = CartRepository.getCartService(jwtToken);
        initlist();
        fetchCartItems();

    }

    private void initlist() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void fetchCartItems() {
        Call<Cart[]> call = cartService.GetCartOfAccount();
        call.enqueue(new Callback<Cart[]>() {
            @Override
            public void onResponse(Call<Cart[]> call, Response<Cart[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Cart[] cartItems = response.body();
                    updateCart(cartItems);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to load cart items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cart[]> call, Throwable t) {
                Toast.makeText(CartActivity.this, "API call failed", Toast.LENGTH_LONG).show();
                Log.e("CartActivity", "Error fetching cart items", t);
            }
        });
    }

    private void updateCart(Cart[] cartItems) {
        ArrayList<Cart> cartItemsList = new ArrayList<>();
        for (Cart item : cartItems) {
            item.getProduct().setPic("milk");
            cartItemsList.add(item);
        }
        adapter = new CartAdapter(cartItemsList, cartService, jwtToken);
        recyclerView.setAdapter(adapter);
    }
}