package com.example.movies.screen.login

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.repository.AppRepository
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginPasswordViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): ViewModel() {

    val isSuccess = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()




    fun loginUser(email: String, password: String){

        viewModelScope.launch {
            try {
                val response = firebaseAuth.signInWithEmailAndPassword(email,password).await()
                if(!response.user?.email.isNullOrEmpty()){
                    isSuccess.value = true
                }
            }catch (e:Exception){
                isError.value = e.localizedMessage
            }
        }
    }







}