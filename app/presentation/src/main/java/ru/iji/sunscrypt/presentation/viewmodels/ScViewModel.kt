package ru.iji.sunscrypt.presentation.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.iji.sunscrypt.domain.usecases.GetCoin
import ru.iji.sunscrypt.domain.usecases.GetCoins
import ru.iji.sunscrypt.presentation.R
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.state.CoinState
import ru.iji.sunscrypt.presentation.state.UiState
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ScViewModel @Inject constructor(
    private val getCoin: GetCoin,
    private val getCoins: GetCoins
) : ViewModel() {

    private val _intent = MutableStateFlow<Intent?>(null)
    val intent = _intent.asStateFlow()

    private val _coinStates = MutableStateFlow<List<CoinState>>(emptyList())
    private val _errorMessage = MutableStateFlow<Int?>(null)
    private val _searchQuery = MutableStateFlow<String>("")
    private val _tagQuery = MutableStateFlow<String>("")

    val uiState = combine(
        _coinStates,
        _errorMessage,
        _searchQuery,
        _tagQuery,
        ::combineState
    ).stateIn(
        scope = viewModelScope,
        initialValue = UiState(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    private fun combineState(
        coinStates: List<CoinState>,
        errorMessage: Int?,
        searchQuery: String,
        tagQuery: String
    ) = UiState(
        coinStates = coinStates
            .filter { it.coin.name.contains(searchQuery, true) },
//            .filter { it.coin.tags.any { tag -> tag.id == tagQuery } },
        isLoading = coinStates.isEmpty(),
        errorMessage = errorMessage
    )

    private fun requestCoins() {
        viewModelScope.launch {
            getCoins()
                .onSuccess { coins ->
                    _coinStates.update {
                        coins.map {
                            CoinState(coin = it)
                        }
                    }

                    _errorMessage.value = null
                }

                .onFailure {
                    _errorMessage.value = when (it) {
                        is IOException -> R.string.error_internet_connection
                        else -> R.string.request_limit_exceeded
                    }
                }
        }
    }

    private fun reloadCoin(coinState: CoinState?) {
        coinState?.let { cs ->
            if (!_coinStates.value.first { it == cs }.isLoaded)
                viewModelScope.launch {
                    getCoin(cs.coin.id)
                        .onSuccess { coin ->
                            _coinStates.update {
                                it.toMutableList()
                                    .apply {
                                        set(
                                            indexOfFirst { it == cs },
                                            cs.copy(
                                                coin = coin,
                                                isLoaded = true,
                                                errorMessage = null
                                            )
                                        )
                                    }
                            }
                        }

                        .onFailure {
                            _coinStates.value = _coinStates.value
                                .toMutableList()
                                .apply {
                                    set(
                                        indexOfFirst { it == cs },
                                        cs.copy(
                                            errorMessage = when (it) {
                                                is IOException -> R.string.error_internet_connection
                                                else -> R.string.request_limit_exceeded
                                            }
                                        )
                                    )
                                }
                        }
                }
        }
    }

    private fun resetCoins() {
        _coinStates.value = emptyList()
        _errorMessage.value = null
        _searchQuery.value = ""
        _tagQuery.value = ""
        requestCoins()
    }

    private fun findCoin(input: String) {
        _searchQuery.value = input
    }

    private fun filterCoins(tag: String) {
        _tagQuery.value = tag
    }

    private fun sendIntent(intent: Intent?) {
        _intent.value = intent
    }

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.LoadCoins -> requestCoins()
            is UiEvent.ResetCoins -> resetCoins()
            is UiEvent.CoinClicked -> reloadCoin(uiEvent.coinState)
            is UiEvent.FindCoin -> findCoin(uiEvent.searchQuery)
            is UiEvent.FilterCoins -> filterCoins(uiEvent.tagQuery)
            is UiEvent.SendIntent -> sendIntent(uiEvent.intent)
        }
    }
}