package com.example.projectmilkshop.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Api.CartRepository;
import com.example.projectmilkshop.Api.CartService;
import com.example.projectmilkshop.Api.ProductRepository;
import com.example.projectmilkshop.Api.ProductService;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailActivity extends AppCompatActivity {

    private static final String TAG = "ShowDetailActivity";
    ProductService productService;
    private ImageView imgMilk, btnMinusQuality, btnAddQuality;
    private TextView tvDetailTitle, tvDetailPrice, tvItemQuality, tvDetailDescription, tvDetailTotal, tvDetailAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productService = ProductRepository.getProductService();

        imgMilk = findViewById(R.id.imgMilk);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvItemQuality = findViewById(R.id.tvItemQuality);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailTotal = findViewById(R.id.tvDetailTotal);
        tvDetailAddToCart = findViewById(R.id.tvDetailAddToCart);
        btnMinusQuality = findViewById(R.id.btnMinusQuality);
        btnAddQuality = findViewById(R.id.btnAddQuality);

        btnMinusQuality.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvItemQuality.getText().toString());
            if (quantity > 1) {
                quantity--;
                tvItemQuality.setText(String.valueOf(quantity));
                updateTotalPrice();
            }
        });

        btnAddQuality.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvItemQuality.getText().toString());
            quantity++;
            tvItemQuality.setText(String.valueOf(quantity));
            updateTotalPrice();
        });

        tvDetailAddToCart.setOnClickListener(v -> {
            int productId = getIntent().getIntExtra("productId", -1);
            double unitPrice = Double.parseDouble(tvDetailPrice.getText().toString());
            int quantity = Integer.parseInt(tvItemQuality.getText().toString());
            addProductToCart(productId, unitPrice, quantity);
        });

        fetchProductDetails();
        bottomNavigation();
    }

    private void addProductToCart(int productId, double unitPrice, int quantity) {
        CartService cartService = CartRepository.getCartService();
        int accountId = 1;
        Cart cart = new Cart(0, productId, unitPrice, accountId, quantity);
        Call<Cart> call = cartService.AddProductToCart(cart);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Log.i(TAG, "Product added to cart successfully.");
                } else {
                    Log.e(TAG, "Failed to add product to cart: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e(TAG, "Failed to add product to cart", t);
            }
        });
    }
    private void updateTotalPrice() {
        int quantity = Integer.parseInt(tvItemQuality.getText().toString());
        double price = Double.parseDouble(tvDetailPrice.getText().toString());
        double total = quantity * price;
        tvDetailTotal.setText(String.valueOf(total));
    }
    private void fetchProductDetails() {
        int productId = getIntent().getIntExtra("productId", -1);
        Call<Product> call = productService.GetProductById(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    product.getPic();
                    tvDetailTitle.setText(product.getProductName());
                    tvDetailPrice.setText(String.valueOf(product.getProductPrice()));
                    tvDetailDescription.setText(product.getProductDescription());
                    tvDetailTotal.setText(String.valueOf(product.getProductPrice()));
                } else {
                    Log.e(TAG, "Failed to fetch product details: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, "Failed to fetch products", t);
            }
        });
    }
    private void updateUI(Product product) {
        tvDetailTitle.setText(product.getProductName());
        tvDetailPrice.setText(String.valueOf(product.getProductPrice()));
        tvItemQuality.setText(String.valueOf(product.getCapacity()));
        tvDetailDescription.setText(product.getProductDescription());
        tvDetailTotal.setText(String.valueOf(product.getProductPrice()));

        int drawableResourceId = getResources().getIdentifier(product.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(imgMilk);
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
                startActivity(new Intent(ShowDetailActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, ProductActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, CartActivity.class));
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, ChatboxActivity.class));
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, MapActivity.class));
            }
        });

    }
}