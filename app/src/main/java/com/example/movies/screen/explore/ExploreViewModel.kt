package com.example.movies.screen.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.movies.Result
import com.example.movies.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    val allMovies = MutableLiveData<List<Result>>()
    val searchMovies = MutableLiveData<List<Result>>()

    val error = MutableLiveData<String>()


    fun getDiscoverMovies(){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = appRepository.getDiscoverMovies()
                if(response.isSuccessful){
                    response.body()?.results.let{
                        withContext(Dispatchers.Main){
                            allMovies.value = it
                        }
                    }
                }


            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage
                }
            }

        }
    }

    fun search(title: String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = appRepository.search(title)
                if(response.isSuccessful){
                    response.body()?.results.let{
                        withContext(Dispatchers.Main){
                            allMovies.value = it
                        }
                    }
                }


            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage
                }
            }

        }
    }

}