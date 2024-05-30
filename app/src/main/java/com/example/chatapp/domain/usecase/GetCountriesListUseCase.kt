package com.example.chatapp.domain.usecase

import android.app.Activity
import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.CountriesListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCountriesListUseCase @Inject constructor(private val countriesListRepository: CountriesListRepository){
     suspend fun invoke (): Flow<List<CountriesModelItem>>{
        return countriesListRepository.getCountriesList()
    }
}