package com.example.chatapp.presentation.splashScreen

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.ApiManager
import com.example.chatapp.domain.model.CountriesModel
import com.example.chatapp.domain.model.CountriesModelItem
import com.example.chatapp.domain.model.State
import com.example.chatapp.domain.usecase.GetCountriesListUseCase
import com.example.chatapp.domain.usecase.SendVerificationCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val getCountriesListUseCase: GetCountriesListUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _countriesList = MutableStateFlow<List<CountriesModelItem>>(emptyList())
    val countries: StateFlow<List<CountriesModelItem>> get() = _countriesList
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> get() = _message

    init {
        getCountriesList()
    }
    fun getCountriesList() {
        Log.d("apiiiiii method" , "hii")

        _isLoading.value = true
        viewModelScope.launch {
            getCountriesListUseCase.invoke().catch {
                _message.value = it.message.toString()
                Log.d("apiiiiii error" , it.toString())
            }
                .collect {
                _countriesList.value = it
                    Log.d("apiiiiii data" , it.first().name)
                    _isLoading.value = false
                }
        }
    }
}
