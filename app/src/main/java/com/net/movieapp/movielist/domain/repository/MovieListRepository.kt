package com.net.movieapp.movielist.domain.repository

import com.net.movieapp.movielist.domain.model.Movie
import com.net.movieapp.movielist.utlis.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page:Int
    ) : Flow<Resource<List<Movie>>>


    suspend fun getMovie(id: Int) : Flow<Resource<Movie>>
}