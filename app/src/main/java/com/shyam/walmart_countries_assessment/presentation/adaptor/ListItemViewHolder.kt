package com.shyam.walmart_countries_assessment.presentation.adaptor


import androidx.recyclerview.widget.RecyclerView
import com.shyam.walmart_countries_assessment.databinding.CountryListItemBinding
import com.shyam.walmart_countries_assessment.domain.Country


/**
 * ViewHolder for displaying a single country item in the list.
 * Holds the views and binds country data to each UI element.
 */
class ListItemViewHolder(private val binding: CountryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Bind the given country data to the corresponding views.
     * Sets the name, region, code, and capital text fields.
     */
    fun bind(country: Country) {
        with(binding) {
            tvName.text = country.name
            tvRegion.text = country.region
            tvCode.text = country.code
            tvCapital.text = country.capital
        }
    }
}
