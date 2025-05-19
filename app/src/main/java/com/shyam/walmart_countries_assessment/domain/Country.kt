package com.shyam.walmart_countries_assessment.domain

/**
 * This is the clean model used by the domain and presentation layers.
 * It only contains the fields required by the UI.
 */
data class Country(
    val name: String,
    val region: String,
    val code: String,
    val capital: String
)