package com.ajdigital.covid19_mask_stats_nav.ui.records

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ajdigital.covid19_mask_stats_nav.R
import com.ajdigital.covid19_mask_stats_nav.data.database.MaskRecord


import com.ajdigital.covid19_mask_stats_nav.ui.records.ShowRecordsFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_show_records.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRecordRecyclerViewAdapter(
    private val mValues: List<MaskRecord>//,
    //private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyRecordRecyclerViewAdapter.ViewHolder>() {

    //private val mOnClickListener: View.OnClickListener

    init {
        /*mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_show_records, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        val isMaskOnString = if (item.hasMaskOn) "Tak" else "Nie"
        var isMaskCorrectlyOnString = ""
        if (item.isMaskCorrectlyOn != null) {
            isMaskCorrectlyOnString =
                "Czy maska jest założona poprawnie? " + if (item.isMaskCorrectlyOn!!) "Tak" else "Nie" + "\n"
        }

        var reasonOfInvalidMaskString = ""
        if (item.reasonOfInvalidMask != null) {
            reasonOfInvalidMaskString =
                "Powód złego założenia maski: ${item.reasonOfInvalidMask}" + "\n"
        }

        holder.mContentView.text =
                    "Miasto: ${item.cityName}\n" +
                    "Wielkość: ${item.citySize}\n" +
                    "Płeć: ${item.sex}\n" +
                    "Wiek: ${item.ageRange}\n" +
                    "Czy nosi maskę: $isMaskOnString\n" +
                    "$isMaskCorrectlyOnString" +
                    "$reasonOfInvalidMaskString"

        with(holder.mView) {
            tag = item
            //setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
