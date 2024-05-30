package com.example.chatapp.data.repository

import android.app.Activity
import com.example.chatapp.domain.datasource.AuthDataSource
import com.example.chatapp.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource): AuthRepository {
    override fun sendVerificationCode(phoneNumber: String , activity: Activity): Flow<Result<Unit>> {
        return dataSource.sendVerificationCode(phoneNumber = phoneNumber , activity = activity)
    }

    override fun verifyCode(code: String): AuthResult {
        TODO("Not yet implemented")
    }

}