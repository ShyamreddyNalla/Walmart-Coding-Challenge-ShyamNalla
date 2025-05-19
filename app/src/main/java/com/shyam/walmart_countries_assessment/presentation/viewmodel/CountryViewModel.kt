package com.shyam.walmart_countries_assessment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shyam.walmart_countries_assessment.domain.Country
import com.shyam.walmart_countries_assessment.domain.GetCountriesUseCase
import com.shyam.walmart_countries_assessment.utils.UiState
import kotlinx.coroutines.launch

/**
 *  ViewModel that fetches country data using GetCountriesUseCase
 *  and exposes UI state (loading, success, error) to the UI layer.
 */
class CountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
) : ViewModel() {

    // Mutable live data to hold and update the UI state
    private val _uiState = MutableLiveData<UiState<List<Country>>>()

    // Public LiveData for UI to observe without modifying
    val uiState: LiveData<UiState<List<Country>>> = _uiState


    init {
        // Fetches the list of countries as soon as the ViewModel is created
        fetchCountries()
    }

    /*
    Launch a coroutine to collect data from the use case
    Update the UI state LiveData with the latest data or status
    */
    private fun fetchCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect { uiState ->
                _uiState.value = uiState
            }
        }
    }
}

