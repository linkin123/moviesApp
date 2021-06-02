package com.worklin.movieapp.repository

import com.worklin.movieapp.data.local.LocalMovieDataSource
import com.worklin.movieapp.data.model.MovieList
import com.worklin.movieapp.data.model.toMovieEntity
import com.worklin.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpCommingMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }

        return dataSourceLocal.getUpCommingMovies()
    }

    override suspend fun getToRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
        }

        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }

        return dataSourceLocal.getPopularMovies()
    }
}