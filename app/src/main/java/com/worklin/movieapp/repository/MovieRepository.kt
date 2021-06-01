package com.worklin.movieapp.repository

import com.worklin.movieapp.data.model.MovieList

interface MovieRepository {
    suspend fun getUpcomingMovies() : MovieList
    suspend fun getToRatedMovies() : MovieList
    suspend fun getPopularMovies() : MovieList

}