package com.worklin.movieapp.data.remote

import com.worklin.movieapp.application.AppConstants
import com.worklin.movieapp.data.model.MovieList
import com.worklin.movieapp.repository.WebService

class MovieDataSource(private val webservice: WebService) {

    suspend fun getUpCommingMovies() =
        webservice.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies() = webservice.getToRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies() = webservice.getPopularMovies(AppConstants.API_KEY)

}