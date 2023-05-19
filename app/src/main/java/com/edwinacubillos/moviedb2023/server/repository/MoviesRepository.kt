package com.edwinacubillos.moviedb2023.server.repository

import com.edwinacubillos.moviedb2023.server.MovieDB

class MoviesRepository{

    private val apiKey = "ff29f617b45b36aab5aa78a6fa04677f"

    suspend fun loadMovies() = MovieDB.retrofit.loadMovies(apiKey)
}