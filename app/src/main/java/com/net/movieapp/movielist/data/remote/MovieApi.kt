package com.net.movieapp.movielist.data.remote

import com.net.movieapp.movielist.data.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "f8661fa84f8383708f5b4819b13c786d"
        const val ACCESS_TOKEN_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmODY2MWZhODRmODM4MzcwOGY1YjQ4MTliMTNjNzg2ZCIsInN1YiI6IjY0MTk0ZTQyNTY5MGI1MDBhMjE4OWI2MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VvmqK7spX6cceJ4OL0TFAXXLfKa-dAvKXJLDeyh_Kf8"
    }
}