package ru.iji.sunscrypt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.iji.sunscrypt.common.Resource
import ru.iji.sunscrypt.domain.model.CoinModel

interface Repository {

//    val coins: List<CoinModel>

    fun getCoins(): Flow<Resource<List<CoinModel>>>
}