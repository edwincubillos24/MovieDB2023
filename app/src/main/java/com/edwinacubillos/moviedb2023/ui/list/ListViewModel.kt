package com.edwinacubillos.moviedb2023.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.moviedb2023.server.model.Movie
import com.edwinacubillos.moviedb2023.server.model.MoviesList
import com.edwinacubillos.moviedb2023.server.repository.MoviesRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val moviesRepository = MoviesRepository()

    private val _moviesLoaded : MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    val moviesLoaded: LiveData<ArrayList<Movie>> = _moviesLoaded

    fun loadMovies() {
        viewModelScope.launch{
            val moviesList: MoviesList = moviesRepository.loadMovies()
            _moviesLoaded.postValue(moviesList.movies as ArrayList<Movie>)
        }
    }
}
