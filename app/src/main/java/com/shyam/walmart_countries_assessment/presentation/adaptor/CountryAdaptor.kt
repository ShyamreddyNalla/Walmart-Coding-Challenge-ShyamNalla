package com.shyam.walmart_countries_assessment.presentation.adaptor


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shyam.walmart_countries_assessment.databinding.CountryListItemBinding
import com.shyam.walmart_countries_assessment.domain.Country

/**
 * Adapter to display a list of Country items in a RecyclerView.
 * Uses ListAdapter for efficient updates with DiffUtil.
 */
class CountryListAdapter : ListAdapter<Country, ListItemViewHolder>(ItemDiffCallback()) {

    // Inflate the layout for a single list item and create a ViewHolder for it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryListItemBinding.inflate(inflater, parent, false)
        return ListItemViewHolder(binding)
    }

    // Bind the Country data at the given position to the ViewHolder's views
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}