package ru.iji.sunscrypt.presentation.utils

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.delay

@Composable
fun Modifier.scIndication(isActive: () -> Boolean): Modifier {

    val interactionSource = remember { MutableInteractionSource() }
    val event = PressInteraction.Press(Offset.Zero)

    LaunchedEffect(isActive()) {
        while (isActive()) {
            interactionSource.emit(event)
            interactionSource.emit(PressInteraction.Release(event))
            delay(1250)
        }
    }

    return this then Modifier.indication(
        interactionSource = interactionSource,
        indication = LocalIndication.current//rememberRipple(bounded = false)
    )
}