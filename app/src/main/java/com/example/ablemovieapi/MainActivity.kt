package com.example.ablemovieapi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ablemovieapi.adapter.Adapter1
import com.example.ablemovieapi.data.Movie
import com.example.ablemovieapi.data.Poster
import com.example.ablemovieapi.vm.MovieViewModel

class MainActivity : AppCompatActivity() {
    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit) }
    private val loadButton: Button by lazy { findViewById(R.id.load_button) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.RV) }
    private val movieTitle: TextView by lazy { findViewById(R.id.movie_title_value) }
    private val movieRelease: TextView by lazy { findViewById(R.id.movie_release_value) }
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value) }
    private val moviePoster: ImageView by lazy { findViewById(R.id.movie_poster) }
    private val adapter: Adapter1 by lazy { Adapter1() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        val viewModel: MovieViewModel by viewModels()
        viewModel.currentMovie.observe(this) { movie ->
            setMovie(movie)
        }
//        viewModel.getAllImages.observe(this){
//            setMovieImages(it)
//        }
        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()
            if (movieId != null) {


                viewModel.loadMovie(movieId)

            }
        }

    }


    fun setMovie(movie: Movie?) {
        if (movie == null) {
            return
        }
        Log.i("Me", "$movie")
        movieTitle.text = movie.title
        movieRelease.text = movie.releaseDate
        movieBudget.text = movie.budget.toString()

//        Glide.with(this).load(Uri.parse("https://image.tmdb.org/t/p/original/${movie.poster}"))
//            .fitCenter().into(moviePoster)

    }

    fun setMovieImages(poster: List<Poster>) {
        adapter.setItems(poster)
    }


}


//    private val callback = object : retrofit2.Callback<Movie> {
//        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//            if(response.isSuccessful){
//                setMovie(response.body())
//            }
//                else {
//                Toast.makeText(applicationContext, "Query failed!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        override fun onFailure(call: Call<Movie>, t: Throwable) {
//            Toast.makeText(applicationContext, "Query failed!", Toast.LENGTH_SHORT).show()



