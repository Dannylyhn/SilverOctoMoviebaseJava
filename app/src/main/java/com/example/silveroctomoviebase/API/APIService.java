package com.example.silveroctomoviebase.API;

import com.example.silveroctomoviebase.Models.MovieResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("/movie/popular?api_key=0a62ad33bf1055feb628ea894759e93b")
    Call<MovieResponse> getMovies();
}
