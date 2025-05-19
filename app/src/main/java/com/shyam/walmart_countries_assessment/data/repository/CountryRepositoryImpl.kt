package com.shyam.walmart_countries_assessment.data.repository

import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import com.shyam.walmart_countries_assessment.data.remote.CountryApiService
import com.shyam.walmart_countries_assessment.domain.IRepository
import retrofit2.Response

/**
 * Implementation of [IRepository] to fetch country data from the remote API.
 *
 * @property countryApiService The Retrofit API service used to make network calls.
 */
class CountryRepositoryImpl(
    private val countryApiService: CountryApiService,
) : IRepository {

    /**
     * Fetches the list of country details from the API.
     *
     * @return A Retrofit [Response] wrapping a list of [CountryEntity] objects.
     * This function is a suspend function because it performs a network call.
     */
    override suspend fun getCountryDetails(): Response<List<CountryEntity>> {
        return countryApiService.getCountries()
    }
}
