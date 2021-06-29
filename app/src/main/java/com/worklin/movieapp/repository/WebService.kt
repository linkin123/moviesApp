package com.worklin.movieapp.repository

import com.google.gson.GsonBuilder
import com.worklin.movieapp.application.AppConstants
import com.worklin.movieapp.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getToRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}

object RetrofitClient{
    val webService by lazy {
        ServiceGenerator.RetrofitEncrypt().create(WebService::class.java)

    }
}