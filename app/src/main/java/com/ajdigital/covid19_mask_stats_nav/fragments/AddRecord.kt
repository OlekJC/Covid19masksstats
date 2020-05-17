package com.ajdigital.covid19_mask_stats_nav.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ajdigital.covid19_mask_stats_nav.R
import kotlinx.android.synthetic.main.fragment_add_record.*

class AddRecord : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
    }

    private fun prepareUI() {
        invalid_mask_reason.isEnabled = false

        has_mask_switch.setOnCheckedChangeListener { p0, isChecked ->
            is_mask_correct_switch.isEnabled = isChecked
            invalid_mask_reason.isEnabled = isChecked
           /* if (isChecked) {
                is_mask_correct_switch.isEnabled = true
                invalid_mask_reason.isEnabled = true
            } else {
                is_mask_correct_switch.isEnabled = false
                invalid_mask_reason.isEnabled = false
            }*/
        }
    }
}
