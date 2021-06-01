package com.worklin.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.worklin.movieapp.R
import com.worklin.movieapp.core.Resource
import com.worklin.movieapp.data.model.Movie
import com.worklin.movieapp.data.remote.MovieDataSource
import com.worklin.movieapp.databinding.FragmentMovieBinding
import com.worklin.movieapp.presentation.MovieViewModel
import com.worklin.movieapp.presentation.MovieViewModelFactory
import com.worklin.movieapp.repository.MovieRepositoryImpl
import com.worklin.movieapp.repository.RetrofitClient
import com.worklin.movieapp.ui.movie.adapters.MovieAdapter
import com.worklin.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.worklin.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.worklin.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie) , MovieAdapter.OnMovieClikListener{


    private lateinit var binding: FragmentMovieBinding
    private val viewmodel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webService)))
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewmodel.fetchMainScreenMovies().observe(viewLifecycleOwner, { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1, UpcomingConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }

                    binding.rvMovies.adapter = concatAdapter

                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_avergage.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.tittle,
            movie.original_languge,
            movie.release_date
        )

        findNavController().navigate(action)
    }


}