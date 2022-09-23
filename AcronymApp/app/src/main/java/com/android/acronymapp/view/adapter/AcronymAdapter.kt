package com.android.acronymapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.acronymapp.databinding.AcronymItemLayoutBinding
import com.android.acronymapp.model.LongForm

class AcronymAdapter() :
    RecyclerView.Adapter<AcronymAdapter.AcronymHolder>() {

    private val mAcronymList: MutableList<LongForm> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val layoutBinding =
            AcronymItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AcronymHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: AcronymHolder, position: Int) {
        holder.bind(mAcronymList[position])
    }

    override fun getItemCount(): Int {
        return mAcronymList.size
    }

    fun addLongForms(venueList: MutableList<LongForm>?) {
        venueList?.let { mAcronymList.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearLongForms() {
        mAcronymList.clear()
        notifyDataSetChanged()
    }

    inner class AcronymHolder(private val binding: AcronymItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // bind the items with each item
        // of the list languageList
        // which than will be
        // shown in recycler view
        fun bind(longForm: LongForm) {
            binding.acronymText.text = longForm.lf
            binding.yearText.text = longForm.since.toString()
        }

    }
}