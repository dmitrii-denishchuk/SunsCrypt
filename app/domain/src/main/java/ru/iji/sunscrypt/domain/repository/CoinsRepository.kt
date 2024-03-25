package ru.iji.sunscrypt.domain.repository

import ru.iji.sunscrypt.domain.models.CoinModel

interface CoinsRepository {

    suspend fun getCoin(id: String): Result<CoinModel>

    suspend fun getCoins(): Result<List<CoinModel>>
}