package com.example.apiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView userId,ids,titleT;
    Button addIntentButton;
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = findViewById(R.id.user_id);
        ids = findViewById(R.id.id);
        titleT = findViewById(R.id.title);

        recyclerView = findViewById(R.id.show);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,true));

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getAllJson().enqueue(new Callback<List<GetModel>>() {
            @Override
            public void onResponse(Call<List<GetModel>> call, Response<List<GetModel>> response) {
                if(response.body().size()>0){
                    ArrayList<GetModel> allApiData = (ArrayList<GetModel>) response.body();

                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,allApiData);

                    recyclerView.setAdapter(recyclerViewAdapter);
                    Toast.makeText(getApplicationContext(),"List is Not Empty",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"List is Empty",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<GetModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}