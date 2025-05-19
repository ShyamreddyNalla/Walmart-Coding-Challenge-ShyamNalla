package com.shyam.walmart_countries_assessment.data.remote

import com.shyam.walmart_countries_assessment.data.constants.Constants
import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import retrofit2.Response
import retrofit2.http.GET

/**
 * Using this to get countries list from the API endpoint.
 *
 * This interface defines a suspend function to fetch a list of CountryEntity objects from the given endpoint.
 * It returns a Retrofit Response object which wraps the list of country data coming from the server.
 */
interface CountryApiService {

    @GET(Constants.COUNTRY_ENDPOINT)
    suspend fun getCountries(): Response<List<CountryEntity>>
}