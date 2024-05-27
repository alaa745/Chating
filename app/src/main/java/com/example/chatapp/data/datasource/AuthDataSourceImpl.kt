package com.example.chatapp.data.datasource

import android.app.Activity
import com.example.chatapp.data.FirebaseManager
import com.example.chatapp.domain.datasource.AuthDataSource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) :  AuthDataSource{
     private val firebaseManager: FirebaseManager = FirebaseManager(firebaseAuth = auth)
    override fun sendVerificationCode(phoneNumber: String , activity: Activity): Flow<Result<Unit>> {
        return firebaseManager.sendVerificationCode(phoneNumber = phoneNumber , activity = activity)
    }

    override fun verifyCode(code: String): AuthResult {
        TODO("Not yet implemented")
    }

}