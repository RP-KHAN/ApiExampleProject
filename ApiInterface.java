package com.example.apiexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("albums")
    Call<List<GetModel>> getAllJson();

    @POST("albums")
    Call<GetModel> createPost(@Body GetModel getModel);

    // delete
    @DELETE("albums/{id}")
    Call<GetModel> deleteData(@Path("id") String id);
}
