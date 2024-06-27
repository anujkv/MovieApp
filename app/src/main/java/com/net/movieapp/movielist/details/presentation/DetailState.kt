package com.net.movieapp.movielist.details.presentation

import com.net.movieapp.movielist.domain.model.Movie

data class DetailState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)