package com.example.projectmilkshop.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmilkshop.Adapter.PopularAdapter;
import com.example.projectmilkshop.Adapter.ProductAdapter;
import com.example.projectmilkshop.Api.ProductRepository;
import com.example.projectmilkshop.Api.ProductService;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    ProductService productService;
    private RecyclerView.Adapter adapter1;
    private RecyclerView recyclerViewProduct;
    private TextView tvMap, tvProfile, tvCart, tvSupport;
    private EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtSearch = findViewById(R.id.edtSearch);
        productService = ProductRepository.getProductService();
        recyclerViewProduct();
        bottomNavigation();
//        adapter = new CartAdapter()
    }

    private void recyclerViewProduct() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewProduct=findViewById(R.id.viewProduct);
        recyclerViewProduct.setLayoutManager(linearLayoutManager);
        ArrayList<Product> mList = new ArrayList<>();

        Call<Product[]> call = productService.GetAllProducts();
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                Product[] products = response.body();
                if (products == null) {
                    Log.e(TAG, "Products response body is null");
                    return;
                }
                for (Product productl : products) {
                    Product product = new Product(productl.getProductId(), productl.getProductName(), productl.getCapacity(), productl.getProductPrice());
                    mList.add(product);
                }
                adapter1 = new ProductAdapter(mList);
                recyclerViewProduct.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                Log.e(TAG, "Failed to fetch products", t);
            }
        });
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
                startActivity(new Intent(ProductActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, ProductActivity.class));
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, MapActivity.class));
            }
        });

    }
}