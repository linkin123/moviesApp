package com.worklin.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.worklin.movieapp.core.BaseConcatHolder
import com.worklin.movieapp.databinding.PopularMovieRowBinding
import com.worklin.movieapp.databinding.UpcomingMovieRowBinding
import com.worklin.movieapp.ui.movie.adapters.MovieAdapter

class UpcomingConcatAdapter (private val moviesAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = UpcomingMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount() = 1

    private inner class ConcatViewHolder(val binding : UpcomingMovieRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvUpcomingMovies.adapter = adapter
        }

    }

}