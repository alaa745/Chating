package com.example.chatapp.data

import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface  ApiManager {
    @GET("assets")
    suspend fun getCountries(): Response<List<CountriesModelItem>>
}