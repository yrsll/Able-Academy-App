package com.example.ablemovieapi.vm

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ablemovieapi.data.Movie
import com.example.ablemovieapi.data.MovieImages
import com.example.ablemovieapi.data.Poster
import com.example.ablemovieapi.repository.MovieRepository
import com.example.ablemovieapi.repository.MovieRepositoryImpl

class MovieViewModel : ViewModel() {
    private val _currentMovie: MutableLiveData<Movie?> = MutableLiveData(null)
    private val _getAllImages: MutableLiveData<MovieImages?> = MutableLiveData(null)
    private val repository: MovieRepository = MovieRepositoryImpl()
    private val callback: MovieRepository.Callback = object : MovieRepository.Callback {
        override fun onMovieLoaded(movie: Movie?) {
            _currentMovie.value = movie
        }

        override fun onMovieImagesLoad(movieImages: MovieImages?) {
            _getAllImages.value = movieImages
        }
    }

    val currentMovie: LiveData<Movie?> = _currentMovie
    val getAllImages: LiveData<MovieImages?> = _getAllImages

    fun loadMovie(movieId: Long) {
        repository.getMovieDetails(movieId, callback)
        repository.getMovieImages(movieId, callback)
    }
}