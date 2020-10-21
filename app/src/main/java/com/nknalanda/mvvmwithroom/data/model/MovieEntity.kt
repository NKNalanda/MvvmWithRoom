package com.nknalanda.mvvmwithroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "movie_name")
    var movieName: String,
    @ColumnInfo(name = "movie_year")
    var movieYear: String
) {

}