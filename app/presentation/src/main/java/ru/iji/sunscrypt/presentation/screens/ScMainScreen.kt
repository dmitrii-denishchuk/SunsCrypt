package ru.iji.sunscrypt.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.iji.sunscrypt.presentation.R
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.state.UiState

@Composable
fun ScMainScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    uiEvent: (UiEvent) -> Unit
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .padding(
                start = dimensionResource(id = R.dimen.large_padding),
                top = dimensionResource(id = R.dimen.large_padding),
                end = dimensionResource(id = R.dimen.large_padding)
            )
    ) {
        ScCoinScreen(
            modifier = modifier,
            uiState = uiState,
            uiEvent = uiEvent
        )
    }
}