package com.worklin.movieapp.data.model

data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val genre_ids: List<Int> = listOf(),
    val backdrop_path: String = "",
    val original_tittle: String = "",
    val original_languge: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val tittle: String = "",
    val video : Boolean = false,
    val vote_avergage : Double = -1.0,
    val vote_count : Int = -1
    )

data class MovieList(
    val results : List<Movie> = listOf()
)
