package com.worklin.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.worklin.movieapp.core.Resource
import com.worklin.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo : MovieRepository) : ViewModel(){

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getPopularMovies(), repo.getToRatedMovies())))
            //Pair<>
            //para 4 o 5 llamadas go to down
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}


class MovieViewModelFactory(private val repo : MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}

//data class NTuple4<T1, T2, T3, T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)
//NTuple(repo.getShalala(), repo.getShalala(), repo.getShalala(), repo.getShalala(), )