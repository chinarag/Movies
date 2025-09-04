package com.example.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table1")
data class MyListModel(
    val title: String,
    val image: String,
    var selected: Boolean = false,
    @PrimaryKey
    var id: Int

)
