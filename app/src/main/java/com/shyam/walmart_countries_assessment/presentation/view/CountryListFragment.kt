package com.shyam.walmart_countries_assessment.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shyam.walmart_countries_assessment.data.repository.CountryRepositoryImpl
import com.shyam.walmart_countries_assessment.data.remote.RetrofitClient
import com.shyam.walmart_countries_assessment.databinding.FragmentCountryBinding
import com.shyam.walmart_countries_assessment.domain.GetCountriesUseCase
import com.shyam.walmart_countries_assessment.presentation.adaptor.CountryListAdapter
import com.shyam.walmart_countries_assessment.presentation.viewmodel.CountryViewModel
import com.shyam.walmart_countries_assessment.presentation.viewmodel.ViewModelFactory
import com.shyam.walmart_countries_assessment.utils.UiState
import com.google.android.material.snackbar.Snackbar
import com.shyam.walmart_countries_assessment.utils.isInternetAvailable

/**
 * Fragment to display the list of countries using RecyclerView
 * Sets up ViewModel, observes UI state, and updates the UI accordingly
 */
class CountryListFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val countryListAdapter by lazy { CountryListAdapter() }

    private lateinit var viewModel: CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory(
            GetCountriesUseCase(
                CountryRepositoryImpl(RetrofitClient.instance)
            )
        )
        viewModel = ViewModelProvider(this, factory)[CountryViewModel::class.java]

        setupRecyclerView()
        observeUiState()
        checkInternetAndShowMessage()
    }

    // Initialize RecyclerView with a LineaLayoutManager and adapter
    private fun setupRecyclerView() {
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countryListAdapter
        }
    }

    // Observes uiState from ViewModel and updates UI based on state
    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                when (state) {
                    is UiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        rvCountries.visibility = View.GONE
                    }

                    is UiState.Success -> {
                        progressBar.visibility = View.GONE
                        rvCountries.visibility = View.VISIBLE
                        countryListAdapter.submitList(state.data)
                    }

                    is UiState.Error -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkInternetAndShowMessage() {
        val isConnected = isInternetAvailable(requireContext())
        binding.tvNoInternet.visibility = if (isConnected) View.GONE else View.VISIBLE
    }

    // Binding is cleared on view destruction to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}