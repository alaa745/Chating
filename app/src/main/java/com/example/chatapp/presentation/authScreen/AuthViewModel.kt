package com.example.chatapp.presentation.authScreen

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.SendVerificationCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val sendVerificationCodeUseCase: SendVerificationCodeUseCase): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isCodeSent = MutableStateFlow(false)
    val isCodeSent: StateFlow<Boolean> get() = _isCodeSent

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> get() = _message
    fun sendVerificationCode(phoneNumber: String , activity: Activity){
        _isLoading.value = true
        viewModelScope.launch {
            sendVerificationCodeUseCase.invoke(phoneNumber = phoneNumber , activity = activity).collect{ result ->
                _isLoading.value = false
                result.onSuccess {
                    _message.value = "Code Sent"
                    _isCodeSent.value = true
                }
                result.onFailure {
                    _message.value = it.message ?: "Error Sending Code"
                    _isCodeSent.value = false
                }
            }
        }
    }
}