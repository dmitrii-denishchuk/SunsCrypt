package ru.iji.sunscrypt.presentation.event

import android.content.Intent
import ru.iji.sunscrypt.presentation.state.CoinState

sealed class UiEvent(
    val searchQuery: String = "",
    val tagQuery: String = "",
    val coinState: CoinState? = null,
    val intent: Intent? = null
) {

    data object LoadCoins : UiEvent()

    data object ResetCoins : UiEvent()

    class CoinClicked(coinState: CoinState) : UiEvent(coinState = coinState)

    class FindCoin(searchQuery: String) : UiEvent(searchQuery = searchQuery)

    class FilterCoins(tagQuery: String) : UiEvent(tagQuery = tagQuery)

    class SendIntent(intent: Intent) : UiEvent(intent = intent)
}