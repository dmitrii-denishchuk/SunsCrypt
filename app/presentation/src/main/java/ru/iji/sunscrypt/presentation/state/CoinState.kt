package ru.iji.sunscrypt.presentation.state

import ru.iji.sunscrypt.domain.models.CoinModel

data class CoinState(
    val coin: CoinModel,
    val isLoaded: Boolean = false,
    val errorMessage: Int? = null
)