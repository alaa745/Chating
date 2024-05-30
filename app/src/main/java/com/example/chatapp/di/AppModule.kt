package com.example.chatapp.di

import com.example.chatapp.ApiCredentials
import com.example.chatapp.LoggingInterceptor
import com.example.chatapp.data.ApiManager
import com.example.chatapp.data.datasource.AuthDataSourceImpl
import com.example.chatapp.data.datasource.CountriesListDataSourceImpl
import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.data.repository.CountriesListRepoImpl
import com.example.chatapp.domain.datasource.AuthDataSource
import com.example.chatapp.domain.datasource.CountriesListDataSource
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.CountriesListRepository
import com.example.chatapp.domain.usecase.GetCountriesListUseCase
import com.example.chatapp.domain.usecase.SendVerificationCodeUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(100 , TimeUnit.SECONDS)
            .readTimeout(100 , TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiCredentials.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiManager {
        return retrofit.create(ApiManager::class.java)
    }

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

    @Provides
    @Singleton
    fun providesCountriesListDataSource(apiManager: ApiManager): CountriesListDataSource = CountriesListDataSourceImpl(apiManager = apiManager)

    @Provides
    @Singleton
    fun providesCountriesListRepo(countriesListDataSource: CountriesListDataSource): CountriesListRepository = CountriesListRepoImpl(countriesListDataSource)

    @Provides
    @Singleton
    fun providesGetCountriesListUseCase(countriesListRepository: CountriesListRepository): GetCountriesListUseCase = GetCountriesListUseCase(countriesListRepository)

}