package com.edwinacubillos.moviedb2023.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.moviedb2023.local.model.LocalMovie
import com.edwinacubillos.moviedb2023.local.repository.LocalMovieRepository
import com.edwinacubillos.moviedb2023.server.model.Movie
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val localMovieRepository = LocalMovieRepository()

    private val _isMovieFavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isMovieFavorite: LiveData<Boolean> = _isMovieFavorite

    fun saveMovie(movie: Movie) {
        val localMovie = LocalMovie(
            id = movie.id,
            title = movie.title,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            overview = movie.overview
        )

        viewModelScope.launch{
            localMovieRepository.saveMovie(localMovie)
        }
    }

    fun searchMovie(id: Int) {
        var movieFavorite = false
        viewModelScope.launch {
            val localMovie = localMovieRepository.searchMovie(id)
            if (localMovie != null)
                movieFavorite = true
            _isMovieFavorite.postValue(movieFavorite)
        }
    }
}