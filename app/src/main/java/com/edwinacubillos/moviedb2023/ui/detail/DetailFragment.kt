package com.edwinacubillos.moviedb2023.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.edwinacubillos.moviedb2023.R
import com.edwinacubillos.moviedb2023.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var isMovieFavorite = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = detailBinding.root

        val movie = args.movie

        detailViewModel.searchMovie(movie.id)

        detailViewModel.isMovieFavorite.observe(viewLifecycleOwner){ isMovieFavorite ->
            this.isMovieFavorite = isMovieFavorite
            if (isMovieFavorite)
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
            else
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
        }

        with(detailBinding){
            titleTextView.text = movie.title
            releaseDateTextView.text = "Release date: " + movie.releaseDate
            voteAverageTextView.text = "Vote average: "+movie.voteAverage.toString()
            summaryTextView.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.posterPath).into(posterImageView)

            favoritesImageView.setOnClickListener {
                if (isMovieFavorite)
                    Toast.makeText(context, "${movie.title} ya esta en tus favoritos", Toast.LENGTH_LONG).show()
                else {
                    isMovieFavorite = true
                    favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                    detailViewModel.saveMovie(movie)
                }
            }
        }
        return view
    }
}