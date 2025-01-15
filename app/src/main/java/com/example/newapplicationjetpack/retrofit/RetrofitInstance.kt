package com.example.newapplicationjetpack.retrofit

import android.os.Build
import com.example.newapplicationjetpack.BuildConfig
import com.example.newapplicationjetpack.network.AuthApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val BASE_URL = BuildConfig.BASE_URL; // Replace with your API's base URL

    val api: AuthApi by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Set connection timeout to 30 seconds
            .readTimeout(30, TimeUnit.SECONDS)     // Set read timeout to 30 seconds
            .writeTimeout(30, TimeUnit.SECONDS)    // Set write timeout to 30 seconds (optional)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Use the configured OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}
