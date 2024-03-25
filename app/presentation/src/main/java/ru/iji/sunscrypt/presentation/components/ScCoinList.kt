package ru.iji.sunscrypt.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import ru.iji.sunscrypt.presentation.R
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.layouts.CoinLayout
import ru.iji.sunscrypt.presentation.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScCoinList(
    modifier: Modifier = Modifier,
    uiState: UiState,
    uiEvent: (UiEvent) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val refreshState = rememberPullToRefreshState()
    var focusedCoinId by remember { mutableStateOf("") }

//    LaunchedEffect(uiState) {
//        if (uiState.isLoading) refreshState.startRefresh()
//        else refreshState.endRefresh()
//    }


    Box(
        modifier = modifier.nestedScroll(refreshState.nestedScrollConnection),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.large_padding)),
            state = lazyListState
        ) {
            if (!refreshState.isRefreshing)
                items(
                    items = uiState.coinStates,
                    key = { it.coin.id }
                ) { coinState ->
                    CoinLayout(
                        modifier = modifier,
                        coinState = coinState,
                        uiEvent = {
                            uiEvent(it)

                            if (it is UiEvent.CoinClicked)
                                focusedCoinId = when (focusedCoinId) {
                                    coinState.coin.id -> ""
                                    else -> coinState.coin.id
                                }
                        },
                        isFocused = { focusedCoinId == coinState.coin.id }
                    )
                }
        }

        ScErrorMessage(
            modifier = modifier.align(Alignment.Center),
            message = uiState.errorMessage
        )

        ScToUpButton(
            modifier = modifier,
            lazyListState = lazyListState
        )

        /**
         * Это решение временное. Необходимо переделать
         */
        if (uiState.isLoading) {
            uiEvent(UiEvent.LoadCoins)
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (refreshState.isRefreshing) {
            uiEvent(UiEvent.ResetCoins)
        }

        if (!uiState.isLoading) refreshState.endRefresh()

        PullToRefreshContainer(state = refreshState)
    }
}