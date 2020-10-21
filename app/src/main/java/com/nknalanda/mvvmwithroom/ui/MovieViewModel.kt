package com.nknalanda.mvvmwithroom.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nknalanda.mvvmwithroom.data.model.MovieEntity
import com.nknalanda.mvvmwithroom.data.repository.MovieRepository

class MovieViewModel(
    application: Application
): AndroidViewModel(application) {

    var repository: MovieRepository = MovieRepository(application)

    fun onAddClick(movieEntity: MovieEntity) {
        repository.insertMovie(movieEntity)
        Log.e("movies", repository.getAllMovies().toString())
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        return repository.getAllMovies()
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        repository.deleteMovie(movieEntity)
    }

    fun updateMovie(movieEntity: MovieEntity) {
        repository.updateMovie(movieEntity)
    }
}