package com.edwinacubillos.moviedb2023

import android.app.Application
import androidx.room.Room
import com.edwinacubillos.moviedb2023.local.MovieDatabase

class MovieDB2023 : Application() {

    companion object {
        lateinit var database: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }
}