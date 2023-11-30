package ru.iji.sunscrypt.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.iji.sunscrypt.domain.model.CoinModel
import ru.iji.sunscrypt.presentation.layouts.CoinLayout
import ru.iji.sunscrypt.presentation.theme.SunsCryptTheme
import ru.iji.sunscrypt.presentation.viewmodels.MainViewModel

@Composable
fun MainScreen() {

    val mainViewModel: MainViewModel = hiltViewModel()
    val state = mainViewModel.state.value
    val coins = emptyList<CoinModel>()// mainViewModel.coins.collectAsStateWithLifecycle(emptyList())

    SunsCryptTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    16.dp
//                    vertical = dimensionResource(
//                        id = R.dimen.size_16dp
                )
//                )
            ) {
                items(
                    items = state.coins
                ) { coin ->
                    CoinLayout(
                        coin = coin
                    )
                }
            }
        }
    }
}