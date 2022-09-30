package com.example.movies_danqi_dzhao40.network;

import com.example.movies_danqi_dzhao40.models.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    public final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    @GET("now_playing?api_key=1cce816c33f277c340282f23ee8df1b4&language=en-US&page=1&region=CA")
    public Call<ResponseObject> getItems();
}
