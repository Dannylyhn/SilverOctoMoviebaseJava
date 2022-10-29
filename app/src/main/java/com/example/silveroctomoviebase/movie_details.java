package com.example.silveroctomoviebase;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class movie_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String title = getIntent().getStringExtra("Title");
        String description = getIntent().getStringExtra("Description");
        int image = getIntent().getIntExtra("IMAGE", 0);
        String posterPath = getIntent().getStringExtra("Poster_path");
        String rating = getIntent().getStringExtra("Rating");
        String releasedate = getIntent().getStringExtra("release");

        TextView titleView = findViewById(R.id.tv_titleDetails);
        TextView descriptionView = findViewById(R.id.tv_descriptiondetails);
        TextView releasedateView = findViewById(R.id.tv_releasedatedetails);
        ImageView imageView = findViewById(R.id.iv_detailsPoster);
        TextView ratingView = findViewById(R.id.tv_ratingdetails);

        titleView.setText(title);
        imageView.setImageResource(image);
        descriptionView.setText(description);
        ratingView.setText(rating);
        releasedateView.setText(releasedate);


    }
}