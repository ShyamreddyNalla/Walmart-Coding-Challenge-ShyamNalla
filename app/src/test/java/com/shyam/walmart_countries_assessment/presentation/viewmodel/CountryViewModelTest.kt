
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.shyam.walmart_countries_assessment.domain.Country
import com.shyam.walmart_countries_assessment.domain.GetCountriesUseCase
import com.shyam.walmart_countries_assessment.presentation.viewmodel.CountryViewModel
import com.shyam.walmart_countries_assessment.utils.UiState
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getCountriesUseCase: GetCountriesUseCase
    private lateinit var viewModel: CountryViewModel

    private val uiStateObserver = mockk<Observer<UiState<List<Country>>>>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCountriesUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun sampleCountries() = listOf(
        Country("India", "Asia", "IN", "New Delhi"),
        Country("USA", "America", "US", "Washington DC")
    )

    @Test
    fun `emits Loading and Success states correctly`() = runTest {
        val flow: Flow<UiState<List<Country>>> = flow {
            emit(UiState.Loading)
            emit(UiState.Success(sampleCountries()))
        }
        every { getCountriesUseCase.invoke() } returns flow

        viewModel = CountryViewModel(getCountriesUseCase)
        viewModel.uiState.observeForever(uiStateObserver)

        testDispatcher.scheduler.advanceUntilIdle()

        val captured = mutableListOf<UiState<List<Country>>>()
        verify(exactly = 2) { uiStateObserver.onChanged(capture(captured)) }

        assertEquals(UiState.Loading, captured[0])
        assertEquals(UiState.Success(sampleCountries()), captured[1])

        viewModel.uiState.removeObserver(uiStateObserver)
    }

    @Test
    fun `emits Loading and Error states correctly`() = runTest {
        val errorMessage = "Offline. Please check your internet."
        val flow: Flow<UiState<List<Country>>> = flow {
            emit(UiState.Loading)
            emit(UiState.Error(errorMessage))
        }
        every { getCountriesUseCase.invoke() } returns flow

        viewModel = CountryViewModel(getCountriesUseCase)
        viewModel.uiState.observeForever(uiStateObserver)

        testDispatcher.scheduler.advanceUntilIdle()

        val captured = mutableListOf<UiState<List<Country>>>()
        verify(exactly = 2) { uiStateObserver.onChanged(capture(captured)) }

        assertEquals(UiState.Loading, captured[0])
        assertEquals(UiState.Error(errorMessage), captured[1])

        viewModel.uiState.removeObserver(uiStateObserver)
    }

    @Test
    fun `use case invoked exactly once on ViewModel init`() = runTest {
        val flow: Flow<UiState<List<Country>>> = flow {
            emit(UiState.Loading)
            emit(UiState.Success(sampleCountries()))
        }
        every { getCountriesUseCase.invoke() } returns flow

        viewModel = CountryViewModel(getCountriesUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        verify(exactly = 1) { getCountriesUseCase.invoke() }
    }
}
