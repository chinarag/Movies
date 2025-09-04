package com.example.movies.screen.seeAll

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.movies.Result
import com.example.movies.repository.AppRepository
import com.example.movies.util.MoviesType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    val movies = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()


    fun getMovies(type: MoviesType){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getMovies(type)
                if (response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            movies.value =it
                        }
                    }
                }

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage
                }
            }
        }
    }




}