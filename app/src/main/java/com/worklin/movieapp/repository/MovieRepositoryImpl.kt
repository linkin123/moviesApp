package com.worklin.movieapp.repository

import com.worklin.movieapp.core.internetCheck
import com.worklin.movieapp.data.local.LocalMovieDataSource
import com.worklin.movieapp.data.model.MovieList
import com.worklin.movieapp.data.model.toMovieEntity
import com.worklin.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (internetCheck.isNetWorkAvailable()) {
            dataSourceRemote.getUpCommingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpCommingMovies()
        } else {
            dataSourceLocal.getUpCommingMovies()

        }

    }

    override suspend fun getToRatedMovies(): MovieList {

        return if (internetCheck.isNetWorkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {

        return if (internetCheck.isNetWorkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }

    }

}