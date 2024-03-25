package ru.iji.sunscrypt.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ScErrorMessage(
    modifier: Modifier = Modifier,
    message: Int?
) {

    message?.let {
        Text(
            modifier = modifier,
            text = stringResource(id = it)
        )
    }
}