package com.example.chatapp.data

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseManager @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    private var verificationId: String? = null
    private var resendingToken: PhoneAuthProvider.ForceResendingToken? = null
     fun sendVerificationCode(phoneNumber: String , activity: Activity): Flow<Result<Unit>> = flow {
        try {
            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity) // Activity (for callback binding)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        throw e
                    }

                    override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                        this@FirebaseManager.verificationId = verificationId
                        resendingToken = token
                        Log.d("codeSent" , "Code Is: $verificationId")
                    }
                }) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            emit(Result.success(Unit))
        } catch (e: Exception){
            emit(Result.failure(e))
        }
    }
}