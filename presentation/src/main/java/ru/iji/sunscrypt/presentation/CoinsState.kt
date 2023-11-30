package ru.iji.sunscrypt.presentation

import ru.iji.sunscrypt.domain.model.CoinModel

data class CoinsState(
    val isLoading: Boolean = false,
    val coins: List<CoinModel> = emptyList(),
    val error: String = ""
)