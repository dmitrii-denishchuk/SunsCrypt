package ru.iji.sunscrypt.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "type") val type: String,
    @Json(name = "logo") val logo: String?,
    @Json(name = "tags") val tags: List<Tag>?,
    @Json(name = "team") val team: List<Team>?,
    @Json(name = "description") val description: String?,
    @Json(name = "message") val message: String?,
    @Json(name = "open_source") val openSource: Boolean?,
    @Json(name = "started_at") val startedAt: String?,
    @Json(name = "development_status") val developmentStatus: String?,
    @Json(name = "hardware_wallet") val hardwareWallet: Boolean?,
    @Json(name = "proof_type") val proofType: String?,
    @Json(name = "org_structure") val orgStructure: String?,
    @Json(name = "hash_algorithm") val hashAlgorithm: String?,
    @Json(name = "contracts") val contracts: List<Contract>?,
    @Json(name = "links") val links: Links?,
    @Json(name = "links_extended") val linksExtended: List<LinksExtended>?,
    @Json(name = "whitepaper") val whitepaper: Whitepaper?,
    @Json(name = "first_data_at") val firstDataAt: String?,
    @Json(name = "last_data_at") val lastDataAt: String?
) {

    @JsonClass(generateAdapter = true)
    data class Tag(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "coin_counter") val coinCounter: Int,
        @Json(name = "ico_counter") val icoCounter: Int
    )

    @JsonClass(generateAdapter = true)
    data class Team(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "position") val position: String
    )

    @JsonClass(generateAdapter = true)
    data class Contract(
        @Json(name = "contract") val contract: String,
        @Json(name = "platform") val platform: String,
        @Json(name = "type") val type: String
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "explorer") val explorer: List<String>?,
        @Json(name = "facebook") val facebook: List<String>?,
        @Json(name = "reddit") val reddit: List<String>?,
        @Json(name = "source_code") val sourceCode: List<String>?,
        @Json(name = "website") val website: List<String>?,
        @Json(name = "youtube") val youtube: List<String>?
    )

    @JsonClass(generateAdapter = true)
    data class LinksExtended(
        @Json(name = "url") val url: String,
        @Json(name = "type") val type: String,
        @Json(name = "stats") val stats: Stats?
    ) {

        @JsonClass(generateAdapter = true)
        data class Stats(
            @Json(name = "followers") val followers: Int?,
            @Json(name = "contributors") val contributors: Int?,
            @Json(name = "stars") val stars: Int?,
            @Json(name = "subscribers") val subscribers: Int?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Whitepaper(
        @Json(name = "link") val link: String?,
        @Json(name = "thumbnail") val thumbnail: String?
    )
}