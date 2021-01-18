package com.example.skywebproandroidapp.viewModel

import android.content.ContentValues.TAG
import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.skywebproandroidapp.model.PhotoItem
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber


class PhotoViewModel(val photoRepository: PhotoRepository): ViewModel() {

    val photoPicsum: MutableLiveData<Resource<PhotoItem>> = MutableLiveData()

    init {
      getPhotoItem()
    }

    fun getPhotoItem() = viewModelScope.launch {
        Timber.d("TAG_GET_NEWS")
        photoPicsum.postValue(Resource.Loading())
        val response = photoRepository.getPhotoItem()
        photoPicsum.postValue(handleSoccerTeamResponse(response))
    }

    private fun handleSoccerTeamResponse(response: Response<PhotoItem>) : Resource<PhotoItem>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}