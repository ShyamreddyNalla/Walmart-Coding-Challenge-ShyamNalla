package com.shyam.walmart_countries_assessment.data.mappers

import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import com.shyam.walmart_countries_assessment.domain.Country

/**
 * Converts a CountryEntity (data from API) to a domain Country model.
 *
 * This method checks if all required fields (name, region, code, capital) are not null.
 * If any of them is null, it returns null to avoid sending incomplete data to the domain layer.
 * This ensures the app always works with clean and valid data only.
 */

fun CountryEntity.toDomain(): Country? {
    val name = this.name ?: return null
    val region = this.region ?: return null
    val code = this.code ?: return null
    val capital = this.capital ?: return null

    return Country(name, region, code, capital)
}
