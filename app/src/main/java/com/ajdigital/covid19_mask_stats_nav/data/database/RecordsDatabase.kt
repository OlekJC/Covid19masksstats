package com.ajdigital.covid19_mask_stats_nav.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MaskRecord::class], version = 1)
abstract class RecordsDatabase : RoomDatabase() {
    abstract fun recordsDao() : RecordsDao

    companion object{
        @Volatile
        private var INSTANCE : RecordsDatabase? = null
        fun getInstance(context: Context) : RecordsDatabase{
            synchronized(this){
                if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RecordsDatabase::class.java,
                        "records_db"
                    ).build()
                }
                return INSTANCE as RecordsDatabase
            }
        }
    }
}