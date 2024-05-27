package com.example.chatapp.di

import com.example.chatapp.data.datasource.AuthDataSourceImpl
import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.domain.datasource.AuthDataSource
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.usecase.SendVerificationCodeUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseInstance(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthDataSource(auth: FirebaseAuth): AuthDataSource = AuthDataSourceImpl(auth = auth)

    @Provides
    @Singleton
    fun providesAuthRepository(authDataSource: AuthDataSource): AuthRepository = AuthRepositoryImpl(authDataSource)

    @Provides
    @Singleton
    fun providesSendVerificationCodeUseCase(authRepository: AuthRepository): SendVerificationCodeUseCase = SendVerificationCodeUseCase(authRepository = authRepository)


}