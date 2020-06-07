package com.ajdigital.covid19_mask_stats_nav.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.ajdigital.covid19_mask_stats_nav.R
import com.ajdigital.covid19_mask_stats_nav.data.viewmodel.StatisticsViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel : StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        add_record.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addRecord)
        }
        upload_records.setOnClickListener {
            Toast.makeText(activity, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        records_list_btn.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_showRecordsFragment)
        }

        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)

        viewModel.records.observe(viewLifecycleOwner, Observer {
            records_tv.text = "Masz ${it.size} rekordów"
        })
    }
}
