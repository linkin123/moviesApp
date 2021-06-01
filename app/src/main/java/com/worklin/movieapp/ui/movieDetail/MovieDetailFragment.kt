package com.worklin.movieapp.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.worklin.movieapp.R
import com.worklin.movieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}")
            .centerCrop()
            .into(binding.imgMovie)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backgroundIMageUrl}")
            .centerCrop()
            .into(binding.imgMovie)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backgroundIMageUrl}")
            .centerCrop()
            .into(binding.imgBackground)

        with(binding) {
            txtTitle.text = args.title
            txtOverview.text = args.overview
            txtLanguage.text = "Lenguaje : ${args.language}"
            txtRaiting.text = "${args.voteAverage} / ${args.voteCount} Reviews"
            txtRelease.text = "Released ${args.releaseDate}"
        }


    }
}