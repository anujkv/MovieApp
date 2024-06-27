package com.net.movieapp.movielist.utlis

sealed class Screen (val rout : String){
    data object Home: Screen("main")
    data object PopularMovieList : Screen("popularMovie")
    data object UpcomingMovieList : Screen("upcomingMovie")
    data object Details : Screen("details")
}