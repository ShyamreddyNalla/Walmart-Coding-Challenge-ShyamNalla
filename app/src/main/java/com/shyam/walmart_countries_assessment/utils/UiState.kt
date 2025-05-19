package com.shyam.walmart_countries_assessment.utils

/**
 * Represents different states of the UI:
 * - Loading: data is being fetched
 * - Success: data was fetched successfully and is available
 * - Error: something went wrong, with an error message
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
