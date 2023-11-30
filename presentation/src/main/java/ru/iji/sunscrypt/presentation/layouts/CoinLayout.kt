package ru.iji.sunscrypt.presentation.layouts

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.iji.sunscrypt.domain.model.CoinModel

@Composable
fun CoinLayout(coin: CoinModel) {

    Card {
        Text(text = coin.id)
    }
}