package ru.iji.sunscrypt.domain.use_cases

import ru.iji.sunscrypt.common.Resource
import kotlinx.coroutines.flow.Flow
import ru.iji.sunscrypt.domain.model.CoinModel
import ru.iji.sunscrypt.domain.repository.Repository

class Coins(private val repository: Repository) {

    operator fun invoke(): Flow<Resource<List<CoinModel>>> = repository.getCoins()
}