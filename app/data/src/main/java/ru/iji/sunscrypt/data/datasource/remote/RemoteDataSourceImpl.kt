package ru.iji.sunscrypt.data.datasource.remote

import ru.iji.sunscrypt.data.mapper.CoinMapper.toCoinModel
import ru.iji.sunscrypt.data.service.CoinPaprika
import ru.iji.sunscrypt.domain.models.CoinModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: CoinPaprika
) : RemoteDataSource {

    override suspend fun getCoin(id: String): Result<CoinModel> {
        return runCatching {
            service.getCoin(id).toCoinModel()
        }
    }

    override suspend fun getCoins(): Result<List<CoinModel>> {
        return runCatching {
            service.getCoins().map {
                it.toCoinModel()
            }
        }
    }
}