package ru.iji.sunscrypt.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import ru.iji.sunscrypt.common.Resource
import ru.iji.sunscrypt.domain.use_cases.Coins
import ru.iji.sunscrypt.presentation.CoinsState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val coins: Coins) : ViewModel() {

    init {
        getCoins()
    }

    private val _state = mutableStateOf(CoinsState())
    val state: State<CoinsState> = _state

    private fun getCoins() {
        coins().onEach {
            when (it) {
                is Resource.Loading -> _state.value = CoinsState(isLoading = true)
                is Resource.Success -> _state.value = CoinsState(coins = it.data ?: emptyList())
                is Resource.Error -> _state.value = CoinsState(error = it.message ?: "")
            }
        }
    }
}