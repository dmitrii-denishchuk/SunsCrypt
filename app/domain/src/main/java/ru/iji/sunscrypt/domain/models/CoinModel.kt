package ru.iji.sunscrypt.domain.models

data class CoinModel(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: String,
    val logo: String?,
    val tags: List<Tag>,
    val team: List<Team>?,
    val description: String?,
    val message: String?,
    val openSource: Boolean?,
    val startedAt: String?,
    val developmentStatus: String?,
    val hardwareWallet: Boolean?,
    val proofType: String?,
    val orgStructure: String?,
    val hashAlgorithm: String?,
    val contracts: List<Contract>?,
    val links: Links?,
    val linksExtended: List<LinksExtended>?,
    val whitepaper: Whitepaper?,
    val firstDataAt: String?,
    val lastDataAt: String?
) {

    data class Tag(
        val id: String,
        val name: String,
        val coinCounter: Int,
        val icoCounter: Int
    )

    data class Team(
        val id: String,
        val name: String,
        val position: String
    )

    data class Contract(
        val contract: String,
        val platform: String,
        val type: String
    )

    data class Links(
        val explorer: List<String>?,
        val facebook: List<String>?,
        val reddit: List<String>?,
        val sourceCode: List<String>?,
        val website: List<String>?,
        val youtube: List<String>?
    )

    data class LinksExtended(
        val url: String,
        val type: String,
        val stats: Stats?
    ) {

        data class Stats(
            val followers: Int?,
            val contributors: Int?,
            val stars: Int?,
            val subscribers: Int?
        )
    }

    data class Whitepaper(
        val link: String?,
        val thumbnail: String?
    )
}