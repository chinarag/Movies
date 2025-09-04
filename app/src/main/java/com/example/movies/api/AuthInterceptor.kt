package com.example.movies.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url

        val urlApiKey = url.newBuilder().addQueryParameter("api_key", "0e3c7bf52adb5a1a383b2f8c42024ede").build()

        val newRequest =  originalRequest.newBuilder().url(urlApiKey).build()

        Log.e("AuthInterceptor", "Request URL: ${newRequest.url}")


        return chain.proceed(newRequest)
    }
}