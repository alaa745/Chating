package com.example.chatapp.data.datasource

import android.util.Log
import com.example.chatapp.data.ApiManager
import com.example.chatapp.domain.datasource.CountriesListDataSource
import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesListDataSourceImpl @Inject constructor(private val apiManager: ApiManager): CountriesListDataSource {
    override suspend fun getCountriesList(): Flow<List<CountriesModelItem>> = flow {
        println("datasource")
        val response = apiManager.getCountries()
        Log.d("apiiiiii data" , "heee")

        if (response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        } else {
            Log.d("erorrrr" , response.code().toString())

            throw Exception("Error fetching countries: ${response.code()}")
        }
    }
}