package com.example.projectmilkshop.Domain;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.projectmilkshop.Api.ProductRepository;
import com.example.projectmilkshop.Api.ProductService;
import com.example.projectmilkshop.BuildConfig;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.BlockThreshold;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.HarmCategory;
import com.google.ai.client.generativeai.type.RequestOptions;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.ai.client.generativeai.type.TextPart;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeminiPro {
    private static final String API_KEY = BuildConfig.API_KEY;

    private static final String BASE_URL = "https://192.168.2.16/api/v1/products/";
    private static GeminiPro instance;
    private ProductService productService;

    private static GenerativeModelFutures model;
    private String dataJson;
    public static GeminiPro getInstance() {
        if (instance == null) {
            instance = new GeminiPro();
        }
        return instance;
    }

    public void getResponse(String query, ResponseCallback callback) {
        try {
            if(model == null) model = getModel();

            Content content = new Content.Builder().addText(query).build();
            Executor executor = Runnable::run;

            ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    String resultText = result.getText();
                    callback.onResponse(resultText);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    throwable.printStackTrace();
                    callback.onError(throwable);
                }
            }, executor);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private GenerativeModelFutures getModel() {

        SafetySetting harassmentSafety = new SafetySetting(HarmCategory.HARASSMENT,
                BlockThreshold.ONLY_HIGH);

        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.temperature = 0.9f;
        configBuilder.topK = 16;
        configBuilder.topP = 0.1f;
        GenerationConfig generationConfig = configBuilder.build();

        productService = ProductRepository.getProductService();
        Call<Product[]> call = productService.GetAllProducts();
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                Product[] products = response.body();
                if (products == null) {
                    Log.e(TAG, "Products response body is null");
                    return;
                } else {
                    Gson gson = new Gson();
                    dataJson = gson.toJson(products);
                }
            }
            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                Log.e(TAG, "Failed to fetch products", t);
            }
        });

        TextPart data = new TextPart(dataJson);

        Content systemInstruction = new Content.Builder()
                .addText("Your name is Javis and you are a customer advisor that support customer in finding milk products based on data I provided")
                .addPart(data)
                .build();

        RequestOptions requestOptions = new RequestOptions(10000L, "v1beta");
        GenerativeModel gm = new GenerativeModel(
                "gemini-1.5-pro",
                API_KEY,
                generationConfig,
                Collections.singletonList(harassmentSafety),
                requestOptions,
                null,
                null,
                systemInstruction
        );

        return GenerativeModelFutures.from(gm);
    }
}
