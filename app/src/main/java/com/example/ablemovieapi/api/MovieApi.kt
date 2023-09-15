package com.example.ablemovieapi.api

import com.example.ablemovieapi.data.Movie
import com.example.ablemovieapi.data.MovieImages
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("movie/{movieId}") // getMovieDetails(5, "sadfs3245ddf") -> server.com/movie/5?api_key=sadfs3245ddf
    fun getMovieDetails(@Path("movieId") movieId: Long,@Query("api_key") apiKey:String):Call<Movie>

    @GET("movie/{movie_id}/images")
    fun getMovieAllImages(@Path("movie_id") movie_id:Long, @Query("api_key") apiKey:String):Call<MovieImages>
    companion object {
        val INSTANCE = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(MovieApi::class.java)

    }
}


