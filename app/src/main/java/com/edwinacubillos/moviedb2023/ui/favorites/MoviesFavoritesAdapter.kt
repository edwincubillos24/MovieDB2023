package com.edwinacubillos.moviedb2023.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.moviedb2023.R
import com.edwinacubillos.moviedb2023.databinding.CardViewMovieItemBinding
import com.edwinacubillos.moviedb2023.local.model.LocalMovie
import com.squareup.picasso.Picasso

class MoviesFavoritesAdapter(
    private val moviesList: ArrayList<LocalMovie>,
    private val onItemClicked: (LocalMovie) -> Unit,
    private val onItemLongClicked: (LocalMovie) -> Unit,
) : RecyclerView.Adapter<MoviesFavoritesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_movie_item, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener { onItemClicked(movie) }
        holder.itemView.setOnLongClickListener { onItemLongClicked(movie)
            true
        }
    }

    fun appendItems(newList: ArrayList<LocalMovie>) {
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewMovieItemBinding.bind(itemView)

        fun bindMovie(movie: LocalMovie) {
            with(binding) {
                titleTextView.text = movie.title
                voteAverageTextView.text = "Vote average " + movie.voteAverage

                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.posterPath).into(pictureImageView)
            }
        }
    }

}