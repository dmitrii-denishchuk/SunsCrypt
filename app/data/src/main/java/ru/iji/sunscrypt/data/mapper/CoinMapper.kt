package ru.iji.sunscrypt.data.mapper

import ru.iji.sunscrypt.data.dto.CoinDto
import ru.iji.sunscrypt.domain.models.CoinModel

object CoinMapper {

    fun CoinDto.toCoinModel() = CoinModel(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        isNew = isNew,
        isActive = isActive,
        type = type,
        logo = logo,
        tags = tags.toTags(),
        team = team?.toTeam(),
        description = description,
        message = message,
        openSource = openSource,
        startedAt = startedAt?.take(10),
        developmentStatus = developmentStatus,
        hardwareWallet = hardwareWallet,
        proofType = proofType,
        orgStructure = orgStructure,
        hashAlgorithm = hashAlgorithm,
        contracts = contracts?.toContracts(),
        links = links?.toLinks(),
        linksExtended = linksExtended?.toLinksExtended(),
        whitepaper = whitepaper?.toWhitepaper(),
        firstDataAt = firstDataAt,
        lastDataAt = lastDataAt
    )

    private fun List<CoinDto.Tag>?.toTags() = this?.map {
        CoinModel.Tag(
            id = it.id,
            name = it.name,
            coinCounter = it.coinCounter,
            icoCounter = it.icoCounter
        )
    } ?: emptyList()

    private fun List<CoinDto.Team>.toTeam() = map {
        CoinModel.Team(
            id = it.id,
            name = it.name,
            position = it.position
        )
    }

    private fun List<CoinDto.Contract>.toContracts() = map {
        CoinModel.Contract(
            contract = it.contract,
            platform = it.platform,
            type = it.type
        )
    }

    private fun CoinDto.Links.toLinks() = CoinModel.Links(
        explorer = explorer,
        facebook = facebook,
        reddit = reddit,
        sourceCode = sourceCode,
        website = website,
        youtube = youtube
    )

    private fun List<CoinDto.LinksExtended>.toLinksExtended() = map {
        CoinModel.LinksExtended(
            url = it.url,
            type = it.type,
            stats = it.stats?.toStats()
        )
    }

    private fun CoinDto.LinksExtended.Stats.toStats() = CoinModel.LinksExtended.Stats(
        followers = followers,
        contributors = contributors,
        stars = stars,
        subscribers = subscribers
    )

    private fun CoinDto.Whitepaper.toWhitepaper() = CoinModel.Whitepaper(
        link = link,
        thumbnail = thumbnail
    )
}