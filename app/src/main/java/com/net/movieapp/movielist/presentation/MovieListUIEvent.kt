package com.net.movieapp.movielist.presentation

sealed interface MovieListUIEvent {
    data class Paginate(val category: String) : MovieListUIEvent

    data object Navigate : MovieListUIEvent

}