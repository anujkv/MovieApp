package com.net.movieapp.di

import com.net.movieapp.movielist.data.Repository.MovieListRepositoryImpl
import com.net.movieapp.movielist.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieListRespositoryImp : MovieListRepositoryImpl
    ) : MovieListRepository
}