package com.shyam.walmart_countries_assessment.presentation.adaptor

import androidx.recyclerview.widget.DiffUtil
import com.shyam.walmart_countries_assessment.domain.Country


/**
 * CountryDiffCallback is used by ListAdapter to efficiently update only the items that have changed.
 * This helps RecyclerView animate changes smartly and avoid unnecessary redraws.
 */

class ItemDiffCallback : DiffUtil.ItemCallback<Country>() {
    // Checks if two Country items represent the same country (using unique 'code').
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }

    // Checks if the actual content of the two Country items is the same.
    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}