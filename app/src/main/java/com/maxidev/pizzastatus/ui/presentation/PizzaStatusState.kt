package com.maxidev.pizzastatus.ui.presentation

import com.maxidev.pizzastatus.data.network.model.PizzaModel

sealed interface PizzaStatusState {
    data class Success(val onSuccess: PizzaModel): PizzaStatusState
    data class Error(val onError: Exception): PizzaStatusState
    data object Loading: PizzaStatusState
    data object Wait: PizzaStatusState
}