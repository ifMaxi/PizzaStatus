package com.maxidev.pizzastatus.data.datasource

import com.maxidev.pizzastatus.data.network.PizzaApiService
import com.maxidev.pizzastatus.data.network.model.PizzaModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PizzaDataSource @Inject constructor(
    private val apiService: PizzaApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchPizzaStatus(code: String?): PizzaModel = withContext(dispatcher) {
        apiService.getPizzaCode(code)
    }
}