package com.worklin.movieapp.data.local

import com.worklin.movieapp.data.model.MovieEntity
import com.worklin.movieapp.data.model.MovieList
import com.worklin.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpCommingMovies()  : MovieList {
        return movieDao.getAllMovies().filter{ it.movie_type == "upcoming" }.toMovieList()
    }


    suspend fun getTopRatedMovies():  MovieList {
        return movieDao.getAllMovies().filter{ it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies() : MovieList {
        return movieDao.getAllMovies().filter{ it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie : MovieEntity){
        movieDao.saveMovie(movie)
    }

}

