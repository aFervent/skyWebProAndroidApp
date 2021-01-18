package com.example.skywebproandroidapp.repository

import com.example.skywebproandroidapp.api.RetrofitInstance

class PhotoRepository {

    suspend fun getPhotoItem() =
        RetrofitInstance.api.getPhoto()
}