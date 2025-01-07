package com.example.finalproject.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalproject.base.MyApplication
import com.example.finalproject.model.Student


@Database(entities = [Student::class], version = 1)
abstract class AppLocalDbRepository: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}


class AppLocalDb {
    val database: AppLocalDbRepository by lazy {
        val context = MyApplication.Globals.context ?: throw IllegalStateException("Application context is missing")
        Room.databaseBuilder(
            context = context,
            klass = AppLocalDbRepository::class.java,
            name = "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}