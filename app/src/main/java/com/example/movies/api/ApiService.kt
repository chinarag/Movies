package com.example.movies.api

import com.example.movies.model.cast.CastResponse
import com.example.movies.model.comment.CommentResponse
import com.example.movies.model.movies.MovieResponse
import com.example.movies.model.movies.Result
import com.example.movies.model.trailer.TrailerResponse
import com.example.movies.util.MoviesType
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{category}")
    suspend fun getMovies(@Path("category") category: String): Response<MovieResponse>

    /*@GET("movie/top_rated")
    suspend fun getTopMovies(): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>*/


    @GET("discover/movie")
    suspend fun getDiscoverMovies(): Response<MovieResponse>

    @GET("search/movie")
    suspend fun search(@Query("query") query: String): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<Result>

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") movieId: Int): Response<CastResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") movieId: Int): Response<TrailerResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(@Path("movie_id") movieId: Int): Response<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getComments(@Path("movie_id") movieId: Int): Response<CommentResponse>





}