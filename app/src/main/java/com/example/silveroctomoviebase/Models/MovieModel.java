package com.example.silveroctomoviebase.Models;

public class MovieModel {
    int movie_ID;
    String movie_title;
    String rating;
    String movie_description;
    String poster_path;
    String release_date;
    int poster_image;

    public MovieModel(int movie_ID, String movie_title, String rating, String movie_description, String poster_path, String release_date, int poster_image) {
        this.movie_ID = movie_ID;
        this.movie_title = movie_title;
        this.rating = rating;
        this.movie_description = movie_description;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.poster_image = poster_image;

    }

    public int getMovie_ID() {
        return movie_ID;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getRating() {
        return rating;
    }

    public int getPoster_image(){
        return poster_image;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
}
