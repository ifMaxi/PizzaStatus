package com.maxidev.pizzastatus.data.repository

import com.maxidev.pizzastatus.data.network.model.PizzaModel

interface PizzaRepository {
    suspend fun getPizza(code: String?): PizzaModel
}