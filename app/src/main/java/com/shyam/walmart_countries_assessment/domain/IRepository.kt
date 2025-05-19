package com.shyam.walmart_countries_assessment.domain

import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import retrofit2.Response

/**
 *  Fetches the list of countries from a data source.
 */
interface IRepository {
    suspend fun getCountryDetails(): Response<List<CountryEntity>>
}