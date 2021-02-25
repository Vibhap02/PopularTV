package com.example.tmbd_api


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmbd_api.detail.TVDetailDao
import com.example.tmbd_api.list.TVListDao

@TypeConverters(DbTypeConverters::class)
@Database(entities = [TV::class],version = 1)
abstract class TVDatabase:RoomDatabase() {
    abstract fun tvListDao(): TVListDao
    abstract fun tvDetailDao(): TVDetailDao
    companion object{
        @Volatile
        private var instance: TVDatabase?=null
        fun getDatabase(context: Context)= instance
                ?: synchronized(this){
            Room.databaseBuilder(
                    context.applicationContext,
                    TVDatabase::class.java,
                    "tv_database"
            ).build().also { instance=it }
        }
    }
}