package com.example.wvLog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wvLog.db.converters.WvLogLevelConverter
import com.example.wvLog.db.dao.WvLogDao
import com.example.wvLog.db.entities.WvLogEntity
import dika.helper.database.converter.DateConverter

@Database(entities = [WvLogEntity::class], version = 1)
@TypeConverters(WvLogLevelConverter::class, DateConverter::class)
abstract class WvLogDatabase: RoomDatabase() {
    abstract fun wvLogDao(): WvLogDao

    companion object {
        private const val DATABASE_NAME = "wv_log_db"

        fun getInstance(context: Context): WvLogDatabase {
            return Room.databaseBuilder(
                context,
                WvLogDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}