package com.example.movies.screen.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.local.MovieDao
import com.example.movies.model.MyListModel
import com.example.movies.model.cast.Cast
import com.example.movies.model.movies.Result
import com.example.movies.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
): ViewModel() {

    val movie = MutableLiveData<Result>()
    val videos = MutableLiveData<List<com.example.movies.model.trailer.Result>>()
    val cast = MutableLiveData<List<Cast>>()
    val similar = MutableLiveData<List<Result>>()
    val comments = MutableLiveData<List<com.example.movies.model.comment.Result>>()

    val error = MutableLiveData<String>()



    fun getMovieDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getMovieDetail(id)

                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.Main){
                            movie.value = it
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

    fun getVideos(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getVideos(id)

                if(response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            videos.value = it
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


    fun getCast(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getCast(movieId)

                if (response.isSuccessful){
                    response.body()?.cast.let {
                        withContext(Dispatchers.Main){
                            cast.value = it
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


    fun getSimilar(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getSimilar(movieId)

                if (response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            similar.value = it
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

    fun getComments(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getComments(movieId)

                if (response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            comments.value = it
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


    fun addMovie(movie: MyListModel){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addMovie(movie)
        }
    }


    fun deleteMovie(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteMovie(id)
        }
    }









}