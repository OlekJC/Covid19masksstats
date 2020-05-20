package com.ajdigital.covid19_mask_stats_nav.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordsDao {
    @Query("SELECT * FROM stats")
    fun getAllRecords(): LiveData<List<MaskRecord>>

    @Insert
    suspend fun insertRecord(record: MaskRecord) : Long
}