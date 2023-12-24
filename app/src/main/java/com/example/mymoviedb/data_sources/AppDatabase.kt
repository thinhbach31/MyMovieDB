package com.example.mymoviedb.data_sources

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

//import com.example.mymoviedb.model.FilmsWithTitleLocalModel

//@Database(entities = [FilmsWithTitleLocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "MovieDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}