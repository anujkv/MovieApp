package com.net.movieapp.movielist.data.Repository

import com.net.movieapp.movielist.data.local.movie.MovieDatabase
import com.net.movieapp.movielist.data.mappers.toMovie
import com.net.movieapp.movielist.data.mappers.toMovieEntity
import com.net.movieapp.movielist.data.remote.MovieApi
import com.net.movieapp.movielist.domain.model.Movie
import com.net.movieapp.movielist.domain.repository.MovieListRepository
import com.net.movieapp.movielist.utlis.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import okio.IOException
import retrofit2.HttpException

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
)
    : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieList(category)

            val shouldLoadLocal = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocal) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMovieList(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let { it.map {
                movieListResponse ->
                movieListResponse.toMovieEntity(category)
            } }
            movieDatabase.movieDao.upsertMovieList(movieEntities)
            emit(Resource.Success(
                movieEntities.map { it.toMovie(category) }
            ))
            emit(Resource.Loading(false))

        }


    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMoviById(id)

            if(movieEntity!=null){
                emit(
                    Resource.Success(movieEntity.toMovie(movieEntity.category))
                )

                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error("Error movie not found!"))
        }
    }
}