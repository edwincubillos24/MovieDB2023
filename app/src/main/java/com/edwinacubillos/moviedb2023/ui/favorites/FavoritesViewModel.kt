package com.edwinacubillos.moviedb2023.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.moviedb2023.local.model.LocalMovie
import com.edwinacubillos.moviedb2023.local.repository.LocalMovieRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val localMovieRepository = LocalMovieRepository()

    private val _favoritesMovies: MutableLiveData<ArrayList<LocalMovie>> = MutableLiveData()
    val favoritesMovies: LiveData<ArrayList<LocalMovie>> = _favoritesMovies

    fun loadFavoritesMovies() {
        viewModelScope.launch {
            val listFavoritesMovies = localMovieRepository.loadFavoritesMovies()
            _favoritesMovies.postValue(listFavoritesMovies as ArrayList<LocalMovie>)
        }
    }

    fun deleteFavoriteMovie(localMovie: LocalMovie) {
        viewModelScope.launch {
            localMovieRepository.deleteFavoriteMovie(localMovie)
        }

    }
}