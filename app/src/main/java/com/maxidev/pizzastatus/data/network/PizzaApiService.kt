package com.maxidev.pizzastatus.data.network

import com.maxidev.pizzastatus.data.network.model.PizzaModel
import com.maxidev.pizzastatus.utils.Constants.CODE_JSON
import retrofit2.http.GET
import retrofit2.http.Path

interface PizzaApiService {
    @GET(CODE_JSON)
    suspend fun getPizzaCode(
        @Path("CODE") code: String?
    ): PizzaModel
}