package com.example.silveroctomoviebase.RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kotlin.experimental.ExperimentalTypeInference;

@Entity
public class Movie {
    @PrimaryKey(autoGenerate = true)
    int id = 0;
    int movie_id;
    String movie_title;
    String rating;
    String movie_description;
    String poster_path;
    String release_date;
    int poster_image;

    public Movie(int id, String movie_title, String rating, String movie_description, String poster_path, String release_date, int poster_image) {
        this.movie_id = id;
        this.movie_title = movie_title;
        this.rating = rating;
        this.movie_description = movie_description;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.poster_image = poster_image;

    }

    public int getMovie_ID() {
        return movie_id;
    }

    public void setMovie_ID(int id) {
        this.movie_id = id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(int poster_image) {
        this.poster_image = poster_image;
    }
}
