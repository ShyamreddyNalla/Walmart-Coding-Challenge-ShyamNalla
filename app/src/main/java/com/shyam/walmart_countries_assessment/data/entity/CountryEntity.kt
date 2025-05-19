package com.shyam.walmart_countries_assessment.data.entity

import com.google.gson.annotations.SerializedName

data class CountryEntity(
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("currency")
    val currency: CurrencyEntity?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("language")
    val language: LanguageEntity?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?
)