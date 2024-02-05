package com.maxidev.pizzastatus.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.pizzastatus.data.network.PizzaApiService
import com.maxidev.pizzastatus.utils.Constants.BASE_URL
import com.maxidev.pizzastatus.utils.Constants.CONTENT_TYPE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(CONTENT_TYPE.toMediaType())
            )
            .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): PizzaApiService =
        retrofit.create(PizzaApiService::class.java)
}