package ru.iji.sunscrypt.presentation.state

data class UiState(
    val coinStates: List<CoinState> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: Int? = null
)