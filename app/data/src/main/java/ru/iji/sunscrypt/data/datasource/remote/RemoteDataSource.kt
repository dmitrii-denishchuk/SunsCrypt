package ru.iji.sunscrypt.data.datasource.remote

import ru.iji.sunscrypt.domain.models.CoinModel

interface RemoteDataSource {

    suspend fun getCoin(id: String): Result<CoinModel>

    suspend fun getCoins(): Result<List<CoinModel>>
}