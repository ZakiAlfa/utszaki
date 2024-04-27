package com.example.utszaki.retrofit;

import com.example.utszaki.response.SearchGithub;
import com.example.utszaki.response.UserGithub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    Call<SearchGithub> searchUsers(@Query("q") String query);

    @GET("users/{username}")
    Call<UserGithub> getUser(@Path("username") String username);

}
