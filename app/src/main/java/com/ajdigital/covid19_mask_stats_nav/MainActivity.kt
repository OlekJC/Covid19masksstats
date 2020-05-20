package com.ajdigital.covid19_mask_stats_nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajdigital.covid19_mask_stats_nav.data.database.MaskRecord

class MainActivity : AppCompatActivity() {

    lateinit var r : MaskRecord

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
