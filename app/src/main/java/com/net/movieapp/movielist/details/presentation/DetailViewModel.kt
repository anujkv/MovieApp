package com.net.movieapp.movielist.details.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.movieapp.movielist.domain.repository.MovieListRepository
import com.net.movieapp.movielist.presentation.MovieListState
import com.net.movieapp.movielist.utlis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val movieId = savedStateHandle.get<Int>("movieId")

    private var detailsState = MutableStateFlow(DetailState())
    val detailState = detailsState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            detailsState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovie(id).collectLatest {
                result ->
                when(result){
                    is Resource.Error -> {
                        detailsState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading ->{
                        detailsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data.let { movie ->
                            detailsState.update {
                                it.copy(movie = movie)
//                                it.copy(isLoading = false)

                            }
                        }

                    }
                }
            }
        }
    }


}