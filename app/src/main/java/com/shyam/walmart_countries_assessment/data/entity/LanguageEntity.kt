package com.shyam.walmart_countries_assessment.data.entity

import com.google.gson.annotations.SerializedName

data class LanguageEntity(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?
)