package com.ajdigital.covid19_mask_stats_nav.data.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdigital.covid19_mask_stats_nav.data.database.MaskRecord
import com.ajdigital.covid19_mask_stats_nav.data.repository.StatisticsRepository
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = StatisticsRepository(application)
    lateinit var records: LiveData<List<MaskRecord>>

    init {
        getAllRecords()
    }

    fun insertRecord(record: MaskRecord) {
        viewModelScope.launch {
            repo.insertRecordToLocalDb(record)
        }
    }

    private fun getAllRecords() {
        records = repo.getAllRecords()
    }
}
