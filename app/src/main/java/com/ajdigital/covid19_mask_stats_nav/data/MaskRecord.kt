package com.ajdigital.covid19_mask_stats_nav.data

data class MaskRecord constructor(
    var cityName :String,
    var citySize :String,
    var voivodeship : String,
    var sex : SEX,
    var ageRange : String,
    var hasMaskOn : Boolean,
    var isMaskCorrectlyOn : Boolean?,
    var reasonOfInvalidMask : String?
) {
    enum class SEX{
        MALE,FEMALE
    }
}