package com.example.utsraihan.api;

import com.example.utsraihan.models.ItemsItem;
import com.example.utsraihan.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    String TOKEN = "";
    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<Response> getUsers(@Query("q") String username);
    @GET("users/{username}")
    Call<ItemsItem> getDetailUser(@Path("username") String username);
}
