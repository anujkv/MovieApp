package com.net.movieapp.movielist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.net.movieapp.movielist.domain.model.Movie
import com.net.movieapp.movielist.presentation.MovieListState
import com.net.movieapp.movielist.presentation.MovieListUIEvent
import com.net.movieapp.movielist.presentation.component.MovieItem
import com.net.movieapp.movielist.utlis.Category

@Composable
fun PopularMovieList(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent : (MovieListUIEvent) -> Unit
//    , viewModel: MovieListViewModel = viewModel()
) {
    /*val listState = rememberLazyGridState(
        initialFirstVisibleItemIndex = viewModel.listState?.firstVisibleItemIndex ?: 0,
        initialFirstVisibleItemScrollOffset = viewModel.listState?.firstVisibleItemScrollOffset ?: 0

    )

    DisposableEffect(Unit) {
        onDispose {
            viewModel.listState = listState
        }
    }*/
    if(movieListState.isLoading){
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment =  Alignment.Center
            ){
            CircularProgressIndicator()
        }
    }else {
        LazyVerticalGrid(
//            state = listState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.popularMovieList.size) {index ->
                MovieItem(
                    movie = movieListState.popularMovieList[index],
                    navHostController = navController
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                if(index >= movieListState.popularMovieList.size-1 && !movieListState.isLoading){
                    onEvent(MovieListUIEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }
}

