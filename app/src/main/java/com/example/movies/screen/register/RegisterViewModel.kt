package com.example.movies.screen.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel() {

    val isSuccess = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()


    fun registerUser(email: String, pass: String){

        viewModelScope.launch {
            try {
                val response = firebaseAuth.createUserWithEmailAndPassword(email,pass).await()
                if(!response.user?.email.isNullOrEmpty()){
                    isSuccess.value = true
                }

            }catch (e: Exception){
                isError.value = e.localizedMessage
            }
        }

    }



}