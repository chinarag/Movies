package com.example.movies.repository

import android.icu.text.CaseMap.Title
import com.example.movies.api.ApiService
import com.example.movies.local.MovieDao
import com.example.movies.model.MyListModel
import com.example.movies.model.cast.CastResponse
import com.example.movies.model.comment.CommentResponse
import com.example.movies.model.movies.MovieResponse
import com.example.movies.model.movies.Result
import com.example.movies.model.trailer.TrailerResponse
import com.example.movies.util.MoviesType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MovieDao
) {


    suspend fun getMovies(category: MoviesType): Response<MovieResponse>{
        return apiService.getMovies(category.type)
    }



    suspend fun getDiscoverMovies(): Response<MovieResponse>{
        return apiService.getDiscoverMovies()
    }


    suspend fun search(title: String): Response<MovieResponse>{
        return apiService.search(title)
    }

    suspend fun getMovieDetail(movie_id: Int): Response<Result>{
        return apiService.getMovieDetail(movie_id)
    }

    suspend fun getCast(movieId: Int): Response<CastResponse>{
        return apiService.getCast(movieId)
    }

    suspend fun getVideos(movieId: Int): Response<TrailerResponse>{
        return apiService.getVideos(movieId)
    }

    suspend fun getSimilar(movieId: Int): Response<MovieResponse>{
        return apiService.getSimilar(movieId)
    }

    suspend fun getComments(movieId: Int): Response<CommentResponse>{
        return apiService.getComments(movieId)
    }

    suspend fun getAllMovies(): List<MyListModel> {
        return dao.getAllMovies()
    }

    suspend fun addMovie(movie: MyListModel){
        return dao.addMovie(movie)
    }

    suspend fun deleteMovie(id: Int) {
        return dao.deleteMovie(id)
    }









}