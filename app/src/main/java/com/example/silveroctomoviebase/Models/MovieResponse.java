package com.example.silveroctomoviebase.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> results;


    public List<Movie> getMovies(){
        return results;
    }
}
