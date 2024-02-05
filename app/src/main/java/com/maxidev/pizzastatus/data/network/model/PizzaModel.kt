package com.maxidev.pizzastatus.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PizzaModel(
    @SerialName("code")
    val code: String,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: String,
    @SerialName("raw")
    val raw: String,
    @SerialName("category")
    val category: String
)