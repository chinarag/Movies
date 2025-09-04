package com.example.movies.di

import android.content.Context
import androidx.room.Room
import com.example.movies.api.ApiService
import com.example.movies.api.AuthInterceptor
import com.example.movies.local.MovieDao
import com.example.movies.local.MovieDatabase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }



    @Singleton
    @Provides
    fun provideOkhttp(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }



    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }




    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ): MovieDatabase{
        return Room.databaseBuilder(context,MovieDatabase::class.java, "movies_db1").build()
    }

    @Singleton
    @Provides
    fun movieDao(
        movieDatabase: MovieDatabase
    ): MovieDao{
        return  movieDatabase.createMovieDao()
    }
}