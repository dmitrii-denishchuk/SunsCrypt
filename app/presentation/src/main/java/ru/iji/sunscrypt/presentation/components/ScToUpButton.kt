package ru.iji.sunscrypt.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import ru.iji.sunscrypt.presentation.R

@Composable
fun BoxScope.ScToUpButton(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState
) {

    val coroutineScope = rememberCoroutineScope()
    val isReturnAvailable by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 9
        }
    }

    AnimatedVisibility(
        modifier = modifier
            .padding(bottom = dimensionResource(id = R.dimen.large_padding))
            .align(Alignment.BottomCenter),
        visible = isReturnAvailable,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = stringResource(id = R.string.scroll_to_top_button_description)
            )
        }
    }
}