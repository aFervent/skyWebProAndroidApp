package com.example.skywebproandroidapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skywebproandroidapp.model.PhotoItem
import com.example.skywebproandroidapp.model.WeatherItem
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.repository.WeatherRepository
import com.example.skywebproandroidapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class WeatherViewModel (val weatherRepository: WeatherRepository): ViewModel() {

    val weatherItem: MutableLiveData<Resource<WeatherItem>> = MutableLiveData()

    init {
        getPhotoItem()
    }

    fun getPhotoItem() = viewModelScope.launch {
        Timber.d("TAG_GET_NEWS")
        weatherItem.postValue(Resource.Loading())
        val response = weatherRepository.getWeatherItem()
        weatherItem.postValue(handleSoccerTeamResponse(response))
    }

    private fun handleSoccerTeamResponse(response: Response<WeatherItem>) : Resource<WeatherItem>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}