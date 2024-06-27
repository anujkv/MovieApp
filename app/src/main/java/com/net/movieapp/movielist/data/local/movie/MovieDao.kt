package com.net.movieapp.movielist.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("Select * from MovieEntity where id = :id")
    suspend fun getMoviById(id:Int): MovieEntity

    @Query("Select * from MovieEntity where category = :category")
    suspend fun getMovieList(category: String): List<MovieEntity>
}