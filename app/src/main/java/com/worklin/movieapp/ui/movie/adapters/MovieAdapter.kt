package com.worklin.movieapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worklin.movieapp.core.BaseViewHolder
import com.worklin.movieapp.data.model.Movie
import com.worklin.movieapp.databinding.MovieItemBinding

class MovieAdapter(private val movieList : List<Movie>, private val itemClickListener : OnMovieClikListener) : RecyclerView.Adapter<BaseViewHolder<*>>(){


    interface OnMovieClikListener{
        fun onMovieClick(movie : Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesBiewHolder(itemBinding, parent.context)

        itemBinding.imgMovie.setOnClickListener{
            val position = holder.adapterPosition.takeIf {  it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener

            itemClickListener.onMovieClick(movieList[position])
        }
        return holder

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesBiewHolder -> holder.bind(movieList[position])

        }
    }

    override fun getItemCount() = movieList.size

    private inner class MoviesBiewHolder(val binding : MovieItemBinding, val context : Context): BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop()
                .into(binding.imgMovie)
        }

    }
}