package com.ajdigital.covid19_mask_stats_nav.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.ajdigital.covid19_mask_stats_nav.data.database.MaskRecord
import com.ajdigital.covid19_mask_stats_nav.data.database.RecordsDatabase

class StatisticsRepository(context: Context){
    private var db: RecordsDatabase = RecordsDatabase.getInstance(context)

    fun getAllRecords(): LiveData<List<MaskRecord>> {
        return db.recordsDao().getAllRecords()
    }

    suspend fun insertRecordToLocalDb(record: MaskRecord) = db.recordsDao().insertRecord(record)
}