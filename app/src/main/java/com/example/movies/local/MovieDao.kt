package com.example.movies.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.model.MyListModel
import com.example.movies.model.movies.Result

@Dao
interface MovieDao {

    @Insert
    fun addMovie(movie: MyListModel)


    @Query("SELECT * FROM movies_table1 ")
    suspend fun getAllMovies(): List<MyListModel>


    @Query("DELETE FROM movies_table1 where id = :id")
    suspend fun deleteMovie(id: Int)


}