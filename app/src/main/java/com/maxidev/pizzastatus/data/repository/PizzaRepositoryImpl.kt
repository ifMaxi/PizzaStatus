package com.maxidev.pizzastatus.data.repository

import com.maxidev.pizzastatus.data.datasource.PizzaDataSource
import com.maxidev.pizzastatus.data.network.model.PizzaModel
import javax.inject.Inject

class PizzaRepositoryImpl @Inject constructor(
    private val dataSource: PizzaDataSource
): PizzaRepository {
    override suspend fun getPizza(code: String?): PizzaModel =
        dataSource.fetchPizzaStatus(code)
}