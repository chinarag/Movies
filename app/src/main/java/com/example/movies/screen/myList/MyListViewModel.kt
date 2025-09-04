package com.example.movies.screen.myList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.local.MovieDao
import com.example.movies.model.MyListModel
import com.example.movies.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    val movies = MutableLiveData<List<MyListModel>>()

    fun getAllMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = appRepository.getAllMovies()
            withContext(Dispatchers.Main){
                movies.value = movieList
            }

            Log.e("gelenData", movieList.toString())
        }
    }


    fun deleteMovie(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteMovie(id)
            val updatedList = appRepository.getAllMovies()
            withContext(Dispatchers.Main) {
                movies.value = updatedList
            }
        }
    }


}