package com.shyam.walmart_countries_assessment.domain

import com.shyam.walmart_countries_assessment.data.mappers.toDomain
import com.shyam.walmart_countries_assessment.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Use case for fetching the list of countries from the repository.
 * It emits UI states: Loading, Success with data, or Error with a message,
 * handling network and other exceptions gracefully.
 */

class GetCountriesUseCase(
    private val repository: IRepository,
) {
    operator fun invoke(): Flow<UiState<List<Country>>> = flow {
        emit(UiState.Loading)
        try {
            val response = repository.getCountryDetails()
            if (response.isSuccessful) {
                // Map DTOs to domain models safely
                val countryList = response.body()?.mapNotNull { it.toDomain() } ?: emptyList()
                emit(UiState.Success(countryList))
            } else {
                val errorMessage = "Server error. Try again later."
                emit(UiState.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(
                UiState.Error(
                    e.localizedMessage ?: "Unexpected error occurred. Please try again later."
                )
            )
        }
    }
}
