package ru.iji.sunscrypt.data.dto

data class CoinDto(
    val symbol: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Long,
    val id: String,
    val type: String
)