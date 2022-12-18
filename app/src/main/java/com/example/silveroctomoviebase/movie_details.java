package com.example.silveroctomoviebase;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class movie_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String IMAGE_BASE = "https://image.tmdb.org/t/p/w500/";

        String title = getIntent().getStringExtra("Title");
        String description = getIntent().getStringExtra("Description");
        String posterPath = getIntent().getStringExtra("Poster_path");
        String rating = getIntent().getStringExtra("Rating");
        String releasedate = getIntent().getStringExtra("release");

        TextView titleView = findViewById(R.id.tv_titleDetails);
        TextView descriptionView = findViewById(R.id.tv_descriptiondetails);
        TextView releasedateView = findViewById(R.id.tv_releasedatedetails);
        ImageView imageView = findViewById(R.id.iv_detailsPoster);
        TextView ratingView = findViewById(R.id.tv_ratingdetails);



        titleView.setText(title);
        Glide.with(this).load(IMAGE_BASE + posterPath).into(imageView);
        descriptionView.setText(description);
        ratingView.setText(rating);
        releasedateView.setText(releasedate);


    }
}