package com.example.skywebproandroidapp.ProviderFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.repository.WeatherRepository
import com.example.skywebproandroidapp.viewModel.PhotoViewModel
import com.example.skywebproandroidapp.viewModel.WeatherViewModel

class WeatherViewModelProviderFactory(
    private val newsRepository: WeatherRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(newsRepository) as T
    }
}