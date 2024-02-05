package com.maxidev.pizzastatus.di

import com.maxidev.pizzastatus.data.datasource.PizzaDataSource
import com.maxidev.pizzastatus.data.network.PizzaApiService
import com.maxidev.pizzastatus.data.repository.PizzaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesDataSource(apiService: PizzaApiService): PizzaDataSource =
        PizzaDataSource(apiService)

    @Provides
    @Singleton
    fun providesRepository(dataSource: PizzaDataSource): PizzaRepositoryImpl =
        PizzaRepositoryImpl(dataSource)
}