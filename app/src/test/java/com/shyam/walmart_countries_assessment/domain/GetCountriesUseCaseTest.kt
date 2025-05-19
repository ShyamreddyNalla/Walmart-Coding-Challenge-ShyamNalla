package com.shyam.walmart_countries_assessment.domain

import com.shyam.walmart_countries_assessment.data.entity.CountryEntity
import com.shyam.walmart_countries_assessment.utils.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response


class GetCountriesUseCaseTest {

    private val repository = mockk<IRepository>()

    private val useCase = GetCountriesUseCase(repository)

    // Sample entities returned by API
    private val countryEntities = listOf(
        CountryEntity(
            capital = "New Delhi",
            code = "IN",
            currency = null,
            flag = null,
            language = null,
            name = "India",
            region = "Asia"
        ),
        CountryEntity(
            capital = "Washington",
            code = "US",
            currency = null,
            flag = null,
            language = null,
            name = "USA",
            region = "North America"
        )
    )


    // Corresponding domain models after mapping
    private val domainCountries = listOf(
        Country("India", "Asia", "IN", "New Delhi"),
        Country("USA", "North America", "US", "Washington")
    )

    @Test
    fun `invoke emits Loading and Success with domain model list on successful response`() = runBlocking {
        coEvery { repository.getCountryDetails() } returns Response.success(countryEntities)

        val emissions = useCase().toList()

        assert(emissions[0] is UiState.Loading)

        val successState = emissions[1]
        assert(successState is UiState.Success)

        if (successState is UiState.Success) {
            assertEquals(domainCountries, successState.data)
        }
    }


    @Test
    fun `invoke emits Error on unsuccessful response`() = runBlocking {
        val response = Response.error<List<CountryEntity>>(500, mockk(relaxed = true))

        coEvery { repository.getCountryDetails() } returns response

        val emissions = useCase().toList()

        assert(emissions[0] is UiState.Loading)

        val errorState = emissions[1]
        assert(errorState is UiState.Error)

        if (errorState is UiState.Error) {
            assertEquals("Server error. Try again later.", errorState.message)
        }
    }
}
