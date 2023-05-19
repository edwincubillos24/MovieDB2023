package com.edwinacubillos.moviedb2023.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edwinacubillos.moviedb2023.local.model.LocalMovie

@Database(entities = [LocalMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun MovieDao(): MovieDao

}