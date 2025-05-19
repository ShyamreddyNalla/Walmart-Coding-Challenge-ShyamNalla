package com.shyam.walmart_countries_assessment.data.repository

import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import com.shyam.walmart_countries_assessment.data.entity.CurrencyEntity
import com.shyam.walmart_countries_assessment.data.entity.LanguageEntity
import com.shyam.walmart_countries_assessment.data.remote.CountryApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CountryRepositoryImplTest {

    private lateinit var countryApiService: CountryApiService
    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setup() {
        countryApiService = mockk()
        repository = CountryRepositoryImpl(countryApiService)
    }

    @Test
    fun `getCountryDetails returns success response from API`() = runBlocking {
        // Arrange
        val expectedCountry = CountryEntity(
            name = "India",
            region = "Asia",
            code = "IN",
            capital = "New Delhi",
            currency = CurrencyEntity("INR", "Indian Rupee", "₹"),
            language = LanguageEntity("hi", "Hindi"),
            flag = "🇮🇳"
        )
        val fakeResponse = Response.success(listOf(expectedCountry))
        coEvery { countryApiService.getCountries() } returns fakeResponse

        // Act
        val result = repository.getCountryDetails()

        // Assert
        assertEquals(200, result.code())
        assertEquals(1, result.body()?.size)
        assertEquals("India", result.body()?.get(0)?.name)

        coVerify(exactly = 1) { countryApiService.getCountries() }
    }

    @Test
    fun `getCountryDetails returns error response from API`() = runBlocking {
        // Arrange
        val errorResponse = Response.error<List<CountryEntity>>(404,
            "Not Found".toResponseBody(null)
        )
        coEvery { countryApiService.getCountries() } returns errorResponse

        // Act
        val result = repository.getCountryDetails()

        // Assert
        assertEquals(false, result.isSuccessful)
        assertEquals(404, result.code())

        coVerify(exactly = 1) { countryApiService.getCountries() }
    }
}
