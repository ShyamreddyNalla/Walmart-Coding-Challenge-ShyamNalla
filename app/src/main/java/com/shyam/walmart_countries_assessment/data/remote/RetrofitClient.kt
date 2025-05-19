package com.shyam.walmart_countries_assessment.data.remote

import com.shyam.walmart_countries_assessment.data.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to create and provide a Retrofit instance configured with:
 * - Base URL from constants
 * - OkHttpClient with logging interceptor for debugging network requests
 * - Gson converter to parse JSON responses into Kotlin objects
 */
object RetrofitClient {

    /**
     * Lazily initialized ApiService instance.
     * Retrofit is built once when this is first accessed.
     */
    val instance: CountryApiService by lazy {

        // Create a logging interceptor to log full HTTP request and response bodies,
        // useful for debugging API calls during development.
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Build OkHttpClient and add the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Build Retrofit instance with base URL, OkHttp client, and Gson converter
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create and return the implementation of ApiService interface
        retrofit.create(CountryApiService::class.java)
    }
}