package com.example.chatapp.domain.usecase

import android.app.Activity
import com.example.chatapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SendVerificationCodeUseCase @Inject constructor(private val authRepository: AuthRepository){
    operator fun invoke (phoneNumber: String , activity: Activity): Flow<Result<Unit>>{
        return authRepository.sendVerificationCode(phoneNumber = phoneNumber , activity = activity)
    }
}