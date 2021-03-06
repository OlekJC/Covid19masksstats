package com.ajdigital.covid19_mask_stats_nav.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ajdigital.covid19_mask_stats_nav.R
import com.ajdigital.covid19_mask_stats_nav.data.database.MaskRecord
import com.ajdigital.covid19_mask_stats_nav.data.viewmodel.StatisticsViewModel
import kotlinx.android.synthetic.main.fragment_add_record.*

class AddRecord : Fragment() {
    val TAG = "AddRecord"
    private lateinit var viewModel : StatisticsViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
        prepareUI()
    }

    private fun prepareUI() {
        invalid_mask_reason_spinner.isEnabled = false

        has_mask_switch.setOnCheckedChangeListener { _, isChecked ->
            is_mask_correct_switch.isEnabled = isChecked
            invalid_mask_reason_spinner.isEnabled = !is_mask_correct_switch.isChecked

            if(!isChecked) invalid_mask_reason_spinner.isEnabled = false
        }

        is_mask_correct_switch.setOnCheckedChangeListener{
            _, isChecked -> invalid_mask_reason_spinner.isEnabled = !isChecked
        }

        submit_button.setOnClickListener { parseAndSubmit() }
    }

    private fun parseAndSubmit() {
        if (city_edit_text.text.isNullOrBlank()) {
            showErrorDialog("Wpisz nazwę miasta")
            return
        }

        if (!woman_button.isChecked && !man_button.isChecked) {
            showErrorDialog("Wybierz płeć")
            return
        }

        val wrongMaskReason = if(is_mask_correct_switch.isChecked)
            null else invalid_mask_reason_spinner.selectedItem.toString()
        val sex = if(woman_button.isChecked) "Kobieta" else "Mężczyzna"

        val record =
            MaskRecord(
                city_edit_text.text.toString(),
                city_size_spinner.selectedItem.toString(),
                voivodeships_selector.selectedItem.toString(),
                sex,
                age_spinner.selectedItem.toString(),
                has_mask_switch.isChecked,
                is_mask_correct_switch.isChecked,
                wrongMaskReason
            )
        viewModel.insertRecord(record)
        showErrorDialog("Record inserted")
        navController.navigate(R.id.action_addRecord_to_mainFragment)
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}
