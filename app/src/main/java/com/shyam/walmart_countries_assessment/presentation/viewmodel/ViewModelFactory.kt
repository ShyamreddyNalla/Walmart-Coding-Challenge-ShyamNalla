package com.shyam.walmart_countries_assessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shyam.walmart_countries_assessment.domain.GetCountriesUseCase

/**
 * Factory to create CountryListViewModel with CountryRepository dependency.
 */
class ViewModelFactory(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(getCountriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}