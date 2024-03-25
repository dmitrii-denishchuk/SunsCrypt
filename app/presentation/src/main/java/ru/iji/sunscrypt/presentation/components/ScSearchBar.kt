package ru.iji.sunscrypt.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.iji.sunscrypt.presentation.R
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.event.UiEvent.FindCoin

@Composable
fun ScSearchBar(
    uiEvent: (UiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    var input by rememberSaveable { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    val history = remember { mutableStateListOf<String>() }

    @OptIn(ExperimentalMaterial3Api::class)
    DockedSearchBar(
        modifier = modifier.fillMaxWidth(),
        query = input,
        onQueryChange = {
            input = it
            uiEvent(FindCoin(it))
        },
        onSearch = {
            if (!history.contains(it)) history.add(it)
            isActive = false
            uiEvent(FindCoin(it))
        },
        active = isActive,
        onActiveChange = { isActive = it },
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        placeholder = {
            if (!isActive)
                Text(
                    text = stringResource(id = R.string.app_name),
//                    style = MaterialTheme.typography.headlineMedium
                )
            else
                Text(text = stringResource(id = R.string.search_placeholder))
        },
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_padding))
            ) {
                if (history.isNotEmpty() && isActive)
                    Text(
                        modifier = modifier.clickable(
                            onClick = {
                                input = ""
                                history.clear()
                            },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                        text = stringResource(id = R.string.clear_button),
                        style = MaterialTheme.typography.labelLarge,
                    )

                IconButton(
                    onClick = {
                        isActive = !isActive
                        if (isActive) input = ""
                    }
                ) {
                    Icon(
                        imageVector = if (isActive) Icons.Rounded.Close else Icons.Rounded.Search,
                        contentDescription = null
                    )
                }

            }
        }
    ) {
        repeat(history.size) { index ->

            val historyItem = history[index]

            ListItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        input = historyItem
                        isActive = false
                    },
                headlineContent = { Text(historyItem) },
                leadingContent = {
                    Icon(
                        Icons.Rounded.Star,
                        contentDescription = null
                    )
                }
            )
        }
    }
}