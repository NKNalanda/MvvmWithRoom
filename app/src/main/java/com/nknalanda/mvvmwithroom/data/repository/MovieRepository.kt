package com.nknalanda.mvvmwithroom.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.nknalanda.mvvmwithroom.data.dao.MovieDao
import com.nknalanda.mvvmwithroom.data.database.MovieDatabase
import com.nknalanda.mvvmwithroom.data.model.MovieEntity

class MovieRepository(application: Application) {
    private var dao: MovieDao? = null
    private var movies: LiveData<List<MovieEntity>>? = null

    init {
        val database = MovieDatabase.getAppDatabase(application.applicationContext)
        dao = database?.movieDao()
        movies = dao?.getAllMovies()
        Log.d("All movies", movies.toString())
    }

    fun insertMovie(movieEntity: MovieEntity) {
        dao?.addMovie(movieEntity)
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        dao?.deleteMovie(movieEntity)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        return movies!!
    }

    fun updateMovie(movieEntity: MovieEntity) {
        dao?.updateMovie(movieEntity)
    }
}