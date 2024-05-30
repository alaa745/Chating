package com.example.chatapp.data.repository

import com.example.chatapp.domain.datasource.CountriesListDataSource
import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import com.example.chatapp.domain.repository.CountriesListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesListRepoImpl @Inject constructor(private val countriesListDataSource: CountriesListDataSource): CountriesListRepository {
    override suspend fun getCountriesList(): Flow<List<CountriesModelItem>> {
        return countriesListDataSource.getCountriesList()
    }
}