package ru.iji.sunscrypt.presentation.layouts

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.iji.sunscrypt.domain.models.CoinModel
import ru.iji.sunscrypt.presentation.R
import ru.iji.sunscrypt.presentation.event.UiEvent
import ru.iji.sunscrypt.presentation.event.UiEvent.CoinClicked
import ru.iji.sunscrypt.presentation.state.CoinState
import ru.iji.sunscrypt.presentation.utils.scIndication

@Composable
fun CoinLayout(
    modifier: Modifier = Modifier,
    coinState: CoinState,
    uiEvent: (UiEvent) -> Unit,
    isFocused: () -> Boolean
) {
    Card(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth(),
        onClick = { uiEvent(CoinClicked(coinState)) },
        colors = CardDefaults.cardColors().copy(containerColor = Transparent)
    ) {
        Column(
            modifier = modifier
                .scIndication { isFocused() && !coinState.isLoaded && coinState.errorMessage == null }
                .padding(all = dimensionResource(id = R.dimen.large_padding))
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.weight(1f),
                    text = coinState.coin.name,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (isFocused() && !coinState.coin.logo.isNullOrEmpty()) {
                    @OptIn(ExperimentalGlideComposeApi::class)
                    GlideImage(
                        modifier = modifier.weight(
                            0.1f,
                            false
                        ),
                        model = coinState.coin.logo,
                        contentDescription = stringResource(id = R.string.coin_logo_description),
                        contentScale = ContentScale.FillWidth
                    )
                }

                if (!isFocused() || (isFocused() && coinState.coin.logo.isNullOrEmpty())) {
                    Text(
                        text = buildAnnotatedString {
                            when (coinState.errorMessage) {
                                null -> withStyle(
                                    SpanStyle(
                                        color = when {
                                            coinState.coin.isActive -> MaterialTheme.colorScheme.primary
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    )
                                ) { append(coinState.coin.symbol) }

                                else -> withStyle(
                                    SpanStyle(
                                        color = MaterialTheme.colorScheme.errorContainer
                                    )
                                ) { append(stringResource(id = coinState.errorMessage)) }
                            }
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            AnimatedVisibility(visible = isFocused() && coinState.isLoaded) {
                Column {
                    CoinDescription(
                        modifier = modifier,
                        description = coinState.coin.description
                    )

                    CoinDetails(
                        modifier = modifier,
                        coin = coinState.coin
                    )

                    CoinTeam(
                        modifier = modifier,
                        team = coinState.coin.team
                    )

                    CoinExtendedLinks(
                        modifier = modifier,
                        linksExtended = coinState.coin.linksExtended,
                        event = uiEvent
                    )

                    CoinTags(
                        modifier = modifier,
                        tags = coinState.coin.tags,
                        event = uiEvent
                    )
                }
            }
        }
    }
}

@Composable
private fun CoinTitle(
    modifier: Modifier = Modifier,
    id: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(id = id),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun CoinDescription(
    modifier: Modifier = Modifier,
    description: String?
) {

    if (!description.isNullOrEmpty()) {
        HorizontalDivider(modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.large_padding)))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun CoinTeam(
    modifier: Modifier = Modifier,
    team: List<CoinModel.Team>?
) {

    if (!team.isNullOrEmpty()) {

        var overflow by remember { mutableStateOf(true) }

        CoinTitle(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.large_padding)),
            id = R.string.coin_team_title
        )

        Text(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { overflow = !overflow },
            text = buildAnnotatedString {
                team.forEachIndexed { i, t ->
                    t.let {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) { append(it.name) }

                        append(" - ")

                        withStyle(
                            SpanStyle(
                                fontStyle = FontStyle.Italic
                            )
                        ) { append(it.position) }

                        if (i < team.size - 1)
                            append(", ")
                    }
                }
            },
            maxLines = if (overflow) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun CoinTags(
    modifier: Modifier = Modifier,
    tags: List<CoinModel.Tag>?,
    event: (UiEvent) -> Unit
) {

    tags?.let {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
        ) {
            items(items = it) {
                AssistChip(
                    shape = CircleShape,
                    onClick = { event(UiEvent.FilterCoins(it.id)) },
                    label = {
                        Text(
                            text = it.name + " " + it.coinCounter,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CoinDetails(
    modifier: Modifier = Modifier,
    coin: CoinModel
) {

    if (
        !coin.startedAt.isNullOrEmpty() &&
        !coin.developmentStatus.isNullOrEmpty() &&
        !coin.orgStructure.isNullOrEmpty() &&
        coin.openSource != null &&
        !coin.proofType.isNullOrEmpty() &&
        !coin.hashAlgorithm.isNullOrEmpty() &&
        coin.hardwareWallet != null
    ) {

        CoinTitle(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.large_padding)),
            id = R.string.coin_details_title
        )

        Text(
            modifier = modifier.fillMaxWidth(),
            text = buildString {
                with(coin) {
                    append(
                        stringResource(id = R.string.started_at_field) + startedAt + "\n",
                        stringResource(id = R.string.development_status_field) + developmentStatus + "\n",
                        stringResource(id = R.string.org_structure_field) + orgStructure + "\n",
                        stringResource(id = R.string.open_source_field) +
                                if (openSource == true)
                                    stringResource(id = R.string.yes_answer) + "\n"
                                else
                                    stringResource(id = R.string.no_answer) + "\n",
                        stringResource(id = R.string.proof_type_field) + proofType + "\n",
                        stringResource(id = R.string.hash_algorithm_field) + hashAlgorithm + "\n",
                        stringResource(id = R.string.hardware_wallet_field) +
                                if (hardwareWallet == true)
                                    stringResource(id = R.string.yes_answer)
                                else
                                    stringResource(id = R.string.no_answer)
                    )
                }
            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun CoinExtendedLinks(
    modifier: Modifier = Modifier,
    linksExtended: List<CoinModel.LinksExtended>?,
    event: (UiEvent) -> Unit
) {

    if (!linksExtended.isNullOrEmpty()) {
        Row(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.medium_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    CoinTitle(
                        modifier = modifier.padding(end = dimensionResource(id = R.dimen.medium_padding)),
                        id = R.string.coin_links_title
                    )
                }
                items(
                    items = linksExtended.filter {
                        it.type.contains(
                            Regex("""github|facebook|twitter|reddit|youtube|telegram""")
                        )
                    }
                ) {

                    @OptIn(ExperimentalMaterial3Api::class)
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false,
                    ) {
                        IconButton(
                            onClick = {
                                event(
                                    UiEvent.SendIntent(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(it.url)
                                        )
                                    )
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = when (it.type) {
                                        "github" -> R.drawable.github
                                        "facebook" -> R.drawable.facebook
                                        "twitter" -> R.drawable.twitter
                                        "reddit" -> R.drawable.reddit
                                        "youtube" -> R.drawable.youtube
                                        "telegram" -> R.drawable.telegram
                                        else -> return@IconButton
                                    }
                                ),
                                contentDescription = stringResource(id = R.string.social_network_icon_description)
                            )
                        }
                    }
                }
            }
        }
    }
}