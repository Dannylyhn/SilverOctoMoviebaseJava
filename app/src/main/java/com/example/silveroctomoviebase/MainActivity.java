package com.example.silveroctomoviebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.silveroctomoviebase.API.APIService;
import com.example.silveroctomoviebase.Models.MovieModel;
import com.example.silveroctomoviebase.Models.MovieResponse;
import com.example.silveroctomoviebase.RoomDatabase.Movie;
import com.example.silveroctomoviebase.RoomDatabase.MovieDao;
import com.example.silveroctomoviebase.RoomDatabase.MovieDatabase;
import com.example.silveroctomoviebase.Services.MovieViewAdapter;
import com.example.silveroctomoviebase.Services.MovieViewInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieViewInterface {

    //Contains data directly from the JSON file
    List<Movie> moviestest = new ArrayList<>();

    //Contains the data from the database
    List<Movie> moviesList = new ArrayList<>();
    MovieViewAdapter adapter;
    private Executor executor = Executors.newSingleThreadExecutor();
    MovieDao movieDao;

    //Images used in the first iteration of implementation
    int[] posterImages = {R.drawable.halloweenends, R.drawable.orphan, R.drawable.projectgem, R.drawable.fall, R.drawable.bullettrain,
            R.drawable.fullmetal, R.drawable.hocuspocus, R.drawable.athena, R.drawable.halloweenends, R.drawable.orphan,
            R.drawable.projectgem, R.drawable.fall, R.drawable.bullettrain,
            R.drawable.fullmetal, R.drawable.hocuspocus, R.drawable.athena, R.drawable.halloweenends, R.drawable.orphan, R.drawable.projectgem,
    R.drawable.fall};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDao = MovieDatabase.getInstance(this).movieDao();

        //Add data to database
        executor.execute(new Runnable() {
            @Override
            public void run() {
                setUpMovieModels();
            }
        });

        //getMoviesFromAPI();

        RecyclerView recyclerView = findViewById(R.id.rvPopularMovies);

        adapter = new MovieViewAdapter(this, this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Load the data into the adapter.
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loadMoviesList();
            }
        });

        //Ensure time for data to load.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Load data into the list in the adapter
    private void loadMoviesList(){
        MovieDatabase db = MovieDatabase.getInstance(getApplicationContext());
        moviesList = db.movieDao().getAllMovies();
        adapter.setMovies(moviesList);
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

                //Inside data into a List
                //moviestest.add(new Movie(movie_id, movie_title, rating, movie_description, poster_path, release_date, posterImages[i]));

                //Insert data into Room database.
                movieDao.insert(new com.example.silveroctomoviebase.RoomDatabase.Movie(movie_id, movie_title, rating, movie_description, poster_path, release_date, posterImages[i]));
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
        intent.putExtra("Title", moviesList.get(position).getMovie_title());
        intent.putExtra("Description", moviesList.get(position).getMovie_description());
        intent.putExtra("IMAGE", moviesList.get(position).getPoster_image());
        intent.putExtra("Poster_path", moviesList.get(position).getPoster_path());
        intent.putExtra("Rating", moviesList.get(position).getRating());
        intent.putExtra("release", moviesList.get(position).getRelease_date());

        startActivity(intent);


    }


    /* Attempt to use Retrofit
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
    */

}