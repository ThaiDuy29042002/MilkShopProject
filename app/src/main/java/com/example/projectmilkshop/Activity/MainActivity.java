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

import com.example.projectmilkshop.Adapter.CategoryAdapter;
import com.example.projectmilkshop.Adapter.PopularAdapter;
import com.example.projectmilkshop.Api.ProductRepository;
import com.example.projectmilkshop.Api.ProductService;
import com.example.projectmilkshop.Domain.CategoryDoman;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProductService productService;
    private RecyclerView.Adapter adapter1, adapter2;
    private RecyclerView recylerViewCategory, recyclerViewPopular;
    private TextView tvMap, tvProfile, tvCart, tvSupport, textView5, tvSeeMore;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSeeMore = findViewById(R.id.tvSeeMore);
        textView5 = findViewById(R.id.textView5);
        edtSearch = findViewById(R.id.edtSearch);
        productService = ProductRepository.getProductService();

        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });
        recylerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChatboxActivity.class));
            }
        });



        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

    }

    private void recylerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recylerViewCategory=findViewById(R.id.viewProduct);
        recylerViewCategory.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryDoman> ctList = new ArrayList<>();
        ctList.add(new CategoryDoman("Milk","milk_pint"));
        ctList.add(new CategoryDoman("Grain Milk","grain_milk"));

        adapter1 = new CategoryAdapter(ctList);
        recylerViewCategory.setAdapter(adapter1);

    }
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopular=findViewById(R.id.view2);
        recyclerViewPopular.setLayoutManager(linearLayoutManager);
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
                    Product product = new Product(productl.getProductId() ,productl.getProductName(), productl.getCapacity(), productl.getProductPrice());
                    mList.add(product);
                }
                adapter2 = new PopularAdapter(mList);
                recyclerViewPopular.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                Log.e(TAG, "Failed to fetch products", t);
            }
        });
//        mList.add(new Product(0,"milk","Milk","milk",5,10000));
//        mList.add(new Product(1,"grain_milk","Milk","grain_milk",5,5000));
//        mList.add(new Product(2,"milk","Milk","milk",5,5000));
//
//        adapter2 = new PopularAdapter(mList);
//        recyclerViewPopular.setAdapter(adapter2);

    }
}