package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.projectmilkshop.Api.OrderRepository;
import com.example.projectmilkshop.Api.OrderService;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.Interceptor.SessionManager;
import com.example.projectmilkshop.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartChangeListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private TextView tvTotal;
    private ConstraintLayout btnCheckout;
    private ScrollView scrollView;

    private SessionManager sessionManager;
    private CartService cartService;
    private OrderService orderService;
    private String jwtToken;
    private ArrayList<Cart> cartItemsList = new ArrayList<>();

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
        jwtToken = sessionManager.getJwtToken();
        cartService = CartRepository.getCartService(jwtToken);
        orderService = OrderRepository.getOrderService(jwtToken);

        fetchCartItems();

        bottomNavigation();
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



    private void fetchCartItems() {
        try {
            Call<Cart[]> call = cartService.GetCartOfAccount();
            call.enqueue(new Callback<Cart[]>() {
                @Override
                public void onResponse(Call<Cart[]> call, Response<Cart[]> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Cart[] cartItems = response.body();
                        loadCart(cartItems);
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
        catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
    }

    private void loadCart(Cart[] cartItems) {
        try {
            double total = 0;
            for (Cart cartItem : cartItems) {
                cartItem.getProduct().setPic("milk_pint");
                cartItem.setPic("milk_pint");
                total += cartItem.getQuantity() * cartItem.getUnitPrice();
                cartItemsList.add(cartItem);
            }
            adapter = new CartAdapter(cartItemsList, cartService, jwtToken, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            tvTotal.setText(String.valueOf(total));
        }
        catch (Exception ex) {
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

    @Override
    public void onCartChanged(double totalPrice) {
        tvTotal.setText(String.format(Locale.getDefault(), "%.2f", totalPrice));
    }
}