package com.example.projectmilkshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmilkshop.Adapter.MessageRVAdapter;
import com.example.projectmilkshop.Domain.GeminiPro;
import com.example.projectmilkshop.Domain.MessageModel;
import com.example.projectmilkshop.Domain.ResponseCallback;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

public class ChatboxActivity extends AppCompatActivity {
    private RecyclerView chatsRV;
    private ImageButton sendMsgBtn;
    private EditText inputMsgEdt;
    private static final String USER_KEY = "user";
    private static final String BOT_KEY = "bot";
    private ArrayList<MessageModel> messageModelArrayList;
    private MessageRVAdapter messageRVAdapter;
    private final GeminiPro geminiPro = GeminiPro.getInstance();
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chatbox);

        chatsRV = findViewById(R.id.idRVChats);
        sendMsgBtn = findViewById(R.id.idIBSend);
        inputMsgEdt = findViewById(R.id.idEdtMessage);

        messageModelArrayList = new ArrayList<>();

        if(firstTime) {
            geminiPro.getResponse("quake",null);
            firstTime = false;
        }
        bottomNavigation();
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputMsgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(ChatboxActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String userMsg = inputMsgEdt.getText().toString();
                    sendMessage(userMsg);
                    inputMsgEdt.setText("");
                }
            }
        });
        messageRVAdapter = new MessageRVAdapter(messageModelArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatboxActivity.this, RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(messageRVAdapter);
    }

    private void sendMessage(String userMsg) {
        messageModelArrayList.add(new MessageModel(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        geminiPro.getResponse(userMsg, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                messageModelArrayList.add(new MessageModel(response, BOT_KEY));
                messageRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable error) {
                messageModelArrayList.add(new MessageModel("No response", BOT_KEY));
                messageRVAdapter.notifyDataSetChanged();
                Log.e("Error", error.getMessage());
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
                startActivity(new Intent(ChatboxActivity.this, MainActivity.class));
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatboxActivity.this, ProductActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatboxActivity.this, CartActivity.class));
            }
        });
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatboxActivity.this, ChatboxActivity.class));
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatboxActivity.this, MapActivity.class));
            }
        });
    }
}