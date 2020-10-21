package com.nknalanda.mvvmwithroom.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nknalanda.mvvmwithroom.data.model.MovieEntity

@Dao
interface MovieDao {

    @Insert
    fun addMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)

    @Update
    fun updateMovie(movieEntity: MovieEntity)
}