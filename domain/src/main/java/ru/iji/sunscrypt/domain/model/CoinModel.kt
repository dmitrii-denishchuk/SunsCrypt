package ru.iji.sunscrypt.domain.model

data class CoinModel(
    val symbol: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Long,
    val id: String,
    val type: String
)