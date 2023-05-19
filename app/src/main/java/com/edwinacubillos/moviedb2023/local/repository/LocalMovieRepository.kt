package com.edwinacubillos.moviedb2023.local.repository

import com.edwinacubillos.moviedb2023.MovieDB2023
import com.edwinacubillos.moviedb2023.local.MovieDao
import com.edwinacubillos.moviedb2023.local.model.LocalMovie

class LocalMovieRepository {

    suspend fun saveMovie(localMovie: LocalMovie) {
        val movieDao: MovieDao = MovieDB2023.database.MovieDao()
        movieDao.saveMovie(localMovie)
    }

    suspend fun searchMovie(id: Int): LocalMovie {
        val movieDao = MovieDB2023.database.MovieDao()
        return movieDao.searchMovie(id)
    }

    suspend fun loadFavoritesMovies(): MutableList<LocalMovie> {
        val movieDao = MovieDB2023.database.MovieDao()
        return movieDao.loadFavoritesMovies()
    }

    suspend fun deleteFavoriteMovie(localMovie: LocalMovie) {
        val movieDao = MovieDB2023.database.MovieDao()
        movieDao.deleteMovie(localMovie)
    }

}