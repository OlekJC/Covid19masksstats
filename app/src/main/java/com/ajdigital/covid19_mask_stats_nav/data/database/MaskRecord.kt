package com.ajdigital.covid19_mask_stats_nav.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class MaskRecord constructor(
    var cityName: String,
    var citySize: String,
    var voivodeship: String,
    var sex: String,
    var ageRange: String,
    var hasMaskOn: Boolean,
    var isMaskCorrectlyOn: Boolean?,
    var reasonOfInvalidMask: String?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}