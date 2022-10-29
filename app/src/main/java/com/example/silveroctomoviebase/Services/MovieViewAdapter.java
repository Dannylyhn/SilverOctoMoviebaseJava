package com.example.silveroctomoviebase.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.silveroctomoviebase.Models.MovieModel;
import com.example.silveroctomoviebase.R;

import java.util.ArrayList;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<MovieModel> movies;
    private final MovieViewInterface movieViewInterface;

    public MovieViewAdapter(Context context, ArrayList<MovieModel> movies, MovieViewInterface movieViewInterface){
        this.context = context;
        this.movies = movies;
        this.movieViewInterface = movieViewInterface;
    }

    @NonNull
    @Override
    public MovieViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieViewAdapter.MyViewHolder(view, movieViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewAdapter.MyViewHolder holder, int position) {
        String IMAGE_BASE = "https://image.tmdb.org/t/p/w500/";
        holder.title.setText(movies.get(position).getMovie_title());
        holder.rating.setText(movies.get(position).getRating());
        holder.release.setText(movies.get(position).getRelease_date());
        //holder.poster.setImageResource(movies.get(position).getPoster_image());
        Glide.with(context).load(IMAGE_BASE + movies.get(position).getPoster_path()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;
        TextView rating;
        TextView release;


        public MyViewHolder(@NonNull View itemView, MovieViewInterface movieViewInterface) {
            super(itemView);
            poster = itemView.findViewById(R.id.movie_poster);
            title = itemView.findViewById(R.id.movie_title);
            rating = itemView.findViewById(R.id.tv_rating);
            release = itemView.findViewById(R.id.tv_releasedate);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(movieViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            movieViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

    }
}
