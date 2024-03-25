package ru.iji.sunscrypt.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.iji.sunscrypt.presentation.components.ScCoinList
import ru.iji.sunscrypt.presentation.components.ScSearchBar
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.state.UiState

@Composable
fun ScCoinScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    uiEvent: (UiEvent) -> Unit
) {
    ScSearchBar(
        modifier = modifier,
        uiEvent = uiEvent
    )

    ScCoinList(
        modifier = modifier,
        uiState = uiState,
        uiEvent = uiEvent
    )
}