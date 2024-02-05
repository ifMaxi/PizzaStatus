package com.maxidev.pizzastatus.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.pizzastatus.data.repository.PizzaRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(
    private val repository: PizzaRepositoryImpl
): ViewModel() {
    private val _statusState: MutableStateFlow<PizzaStatusState> =
        MutableStateFlow(PizzaStatusState.Loading)
    val statusState: StateFlow<PizzaStatusState> = _statusState.asStateFlow()

    private var _search: MutableState<String> = mutableStateOf("")
    var search: State<String> = _search

    fun onSearchChange(query: String) {
        _search.value = query
    }

    init {
        deliverPizza()
    }

    fun deliverPizza(code: String? = "") {
        viewModelScope.launch {
            _statusState.value = PizzaStatusState.Loading
            delay(2500)
            _statusState.value = try {
                if (code.isNullOrEmpty()) {
                    PizzaStatusState.Wait
                } else {
                    PizzaStatusState.Success(onSuccess = repository.getPizza(code))
                }
            } catch (ioException: IOException) {
                PizzaStatusState.Error(onError = ioException)
            } catch (httpException: HttpException) {
                PizzaStatusState.Error(onError = httpException)
            }
        }
    }
}