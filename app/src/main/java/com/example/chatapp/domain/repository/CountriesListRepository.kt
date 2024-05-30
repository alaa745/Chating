package com.example.chatapp.domain.repository

import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import kotlinx.coroutines.flow.Flow

interface CountriesListRepository {
    suspend fun getCountriesList(): Flow<List<CountriesModelItem>>
}