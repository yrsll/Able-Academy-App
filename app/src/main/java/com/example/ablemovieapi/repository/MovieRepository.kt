package com.example.ablemovieapi.repository

import com.example.ablemovieapi.data.Movie
import com.example.ablemovieapi.data.MovieImages

interface MovieRepository {
    fun getMovieDetails(movieId: Long, callback: Callback)
    fun getMovieImages(movieId: Long, callback: Callback)


    interface Callback {

        fun onMovieLoaded(movie: Movie?)
        fun onMovieImagesLoad(movieImages: MovieImages?)
    }

}



