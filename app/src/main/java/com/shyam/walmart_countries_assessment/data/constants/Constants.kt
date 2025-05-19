package com.shyam.walmart_countries_assessment.data.constants

/**
 * Object to store constant values used across the app.
 *
 * Helps avoid hardcoding values like URLs in multiple places,
 * making code easier to manage and less error-prone.
 */
object Constants {

    /**
     * Base URL for making API calls.
     * This is the root URL that remains constant across requests.
     */
    const val BASE_URL = "https://gist.githubusercontent.com/"

    /**
     * Endpoint to fetch the list of countries in JSON format.
     * Appended to the BASE_URL when making a network call.
     */
    const val COUNTRY_ENDPOINT =
        "peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"
}
