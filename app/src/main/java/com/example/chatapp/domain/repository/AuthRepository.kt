package com.example.chatapp.domain.repository

import android.app.Activity
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository{
    fun sendVerificationCode(phoneNumber: String , activity: Activity): Flow<Result<Unit>>
    fun verifyCode(code: String): AuthResult
}