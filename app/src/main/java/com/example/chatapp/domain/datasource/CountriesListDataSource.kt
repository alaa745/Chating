package com.example.chatapp.domain.datasource

import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import kotlinx.coroutines.flow.Flow

interface CountriesListDataSource {
   suspend fun getCountriesList(): Flow<List<CountriesModelItem>>

}