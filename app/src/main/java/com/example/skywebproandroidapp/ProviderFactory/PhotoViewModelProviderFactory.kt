package com.example.skywebproandroidapp.ProviderFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.viewModel.PhotoViewModel

class PhotoViewModelProviderFactory (
    private val newsRepository: PhotoRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(newsRepository) as T
    }
}