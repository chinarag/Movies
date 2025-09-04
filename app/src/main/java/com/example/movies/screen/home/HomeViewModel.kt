package com.example.movies.screen.home

import android.util.Log
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
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    val topMovies = MutableLiveData<List<Result>>()
    val popularMovies = MutableLiveData<List<Result>>()
    val upcomingMovies = MutableLiveData<List<Result>>()
    val nowPlayingMovies = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()


fun getNowPlayingMovies(){
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getMovies(MoviesType.NowPlaying)

            if(response.isSuccessful){
                response.body()?.results.let {
                    withContext(Dispatchers.Main){
                        nowPlayingMovies.value = it
                    }
                }
            }else {
                error.value = "No movies found"
            }

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                error.value = e.localizedMessage
            }
        }
    }
}

    fun getTopMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getMovies(MoviesType.Top)

                if(response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            topMovies.value = it
                        }
                    }
                }else {
                    error.value = "No movies found"
                }

            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage
                }
            }
        }
    }


    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appRepository.getMovies(MoviesType.Popular)

                if(response.isSuccessful){
                    response.body()?.results.let {
                        withContext(Dispatchers.Main){
                            popularMovies.value = it
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




fun getUpcomingMovies() {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getMovies(MoviesType.Upcoming)

            if (response.isSuccessful) {
                response.body()?.results.let {
                    withContext(Dispatchers.Main) {
                        upcomingMovies.value = it
                    }
                }
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                error.value = e.localizedMessage
            }
        }
    }
}


/*fun getNowPlayingMovies(){
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getNowPlayingMovies()

            if(response.isSuccessful){
                response.body()?.results.let {
                    withContext(Dispatchers.Main){
                        nowPlayingMovies.value = it
                    }
                }
            }else {
                error.value = "No movies found"
            }

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                error.value = e.localizedMessage
            }
        }
    }
}


fun getTopMovies(){
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getTopMovies()

            if(response.isSuccessful){
                response.body()?.results.let {
                    withContext(Dispatchers.Main){
                        topMovies.value = it
                    }
                }
            }else {
                error.value = "No movies found"
            }

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                error.value = e.localizedMessage
            }
        }
    }
}




fun getPopularMovies(){
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getPopularMovies()

            if(response.isSuccessful){
                response.body()?.results.let {
                    withContext(Dispatchers.Main){
                        popularMovies.value = it
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


fun getUpcomingMovies(){
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = appRepository.getUpcomingMovies()

            if(response.isSuccessful){
                response.body()?.results.let{
                    withContext(Dispatchers.Main){
                        upcomingMovies.value = it
                    }
                }
            }

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                error.value = e.localizedMessage
            }
        }
    }
}*/





}