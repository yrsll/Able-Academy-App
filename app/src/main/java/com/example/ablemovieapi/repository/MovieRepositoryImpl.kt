package com.example.ablemovieapi.repository

import com.example.ablemovieapi.api.MovieApi
import com.example.ablemovieapi.data.Movie
import com.example.ablemovieapi.data.MovieImages
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieRepositoryImpl : MovieRepository {
    override fun getMovieDetails(movieId: Long, callback: MovieRepository.Callback) {
        MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY)
            .enqueue(object : retrofit2.Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        callback.onMovieLoaded(response.body())
                    } else {
                        callback.onMovieLoaded(null)

                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    callback.onMovieLoaded(null)
                }

            })


    }

    override fun getMovieImages(movieId: Long, callback: MovieRepository.Callback) {
        MovieApi.INSTANCE.getMovieAllImages(movieId, API_KEY)
            .enqueue(object : retrofit2.Callback<MovieImages> {
                override fun onResponse(call: Call<MovieImages>, response: Response<MovieImages>) {
                    if (response.isSuccessful) {
                        callback.onMovieImagesLoad(response.body())
                    } else {
                        callback.onMovieImagesLoad(null)
                    }

                }

                override fun onFailure(call: Call<MovieImages>, t: Throwable) {
                    callback.onMovieLoaded(null)
                }

            })
    }

    companion object {
        private val API_KEY = "670f7d72022da5eca8d5c94c8b80781f"
    }
}