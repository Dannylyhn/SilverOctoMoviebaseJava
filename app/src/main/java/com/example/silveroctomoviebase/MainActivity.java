package com.example.silveroctomoviebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.silveroctomoviebase.API.APIService;
import com.example.silveroctomoviebase.Models.Movie;
import com.example.silveroctomoviebase.Models.MovieModel;
import com.example.silveroctomoviebase.Models.MovieResponse;
import com.example.silveroctomoviebase.Services.MovieViewAdapter;
import com.example.silveroctomoviebase.Services.MovieViewInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieViewInterface {

    //Used to send to our recyclerAdapter later on
    //Maybe use a database or fetch to fill this list up
    ArrayList<MovieModel> movies = new ArrayList<>();
    List<Movie> moviesList;


    //First is halloween ends
    int[] posterImages = {R.drawable.halloweenends, R.drawable.orphan, R.drawable.projectgem, R.drawable.fall, R.drawable.bullettrain,
            R.drawable.fullmetal, R.drawable.hocuspocus, R.drawable.athena, R.drawable.halloweenends, R.drawable.orphan,
            R.drawable.projectgem, R.drawable.fall, R.drawable.bullettrain,
            R.drawable.fullmetal, R.drawable.hocuspocus, R.drawable.athena, R.drawable.halloweenends, R.drawable.orphan, R.drawable.projectgem,
    R.drawable.fall};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getMoviesFromAPI();

        RecyclerView recyclerView = findViewById(R.id.rvPopularMovies);

        setUpMovieModels();

        MovieViewAdapter adapter = new MovieViewAdapter(this, movies, this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpMovieModels() {
        //Read from JSON file to put in a list
        String obj = readJSONFromAsset();
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(obj);
            //Get the jsonArray
            String getResults = jsonObj.getJSONArray("results").toString();
            //Get the specific element
            String getElement = jsonObj.getJSONArray("results").getString(0);

            int jsonListLength = jsonObj.getJSONArray("results").length();

            for (int i = 0; i < jsonListLength; i++){
                String movie_title = jsonObj.getJSONArray("results").getJSONObject(i).get("title").toString();
                int movie_id = jsonObj.getJSONArray("results").getJSONObject(i).getInt("id");
                String poster_path = jsonObj.getJSONArray("results").getJSONObject(i).get("poster_path").toString();
                String release_date = jsonObj.getJSONArray("results").getJSONObject(i).get("release_date").toString();
                String rating = jsonObj.getJSONArray("results").getJSONObject(i).get("vote_average").toString();
                String movie_description = jsonObj.getJSONArray("results").getJSONObject(i).get("overview").toString();

                movies.add(new MovieModel(movie_id, movie_title, rating, movie_description, poster_path, release_date, posterImages[i]));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("popular.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, movie_details.class);
        intent.putExtra("Title", movies.get(position).getMovie_title());
        intent.putExtra("Description", movies.get(position).getMovie_description());
        intent.putExtra("IMAGE", movies.get(position).getPoster_image());
        intent.putExtra("Poster_path", movies.get(position).getPoster_path());
        intent.putExtra("Rating", movies.get(position).getRating());
        intent.putExtra("release", movies.get(position).getRelease_date());

        startActivity(intent);

    }


    public void getMoviesFromAPI(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        APIService apiService = retrofit.create(APIService.class);

        Call<MovieResponse> call = apiService.getMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(!response.isSuccessful()){
                    return;
                }
                moviesList = response.body().getMovies();

                for(Movie movie : moviesList){
                    Log.i("Title: ", movie.getTitle());
                }

            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


    }
}