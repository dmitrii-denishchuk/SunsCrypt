package ru.iji.sunscrypt.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.iji.sunscrypt.common.Resource
import ru.iji.sunscrypt.data.api.CoinPaprikaApi
import ru.iji.sunscrypt.data.mapper.CoinMapper.toCoinModel
import ru.iji.sunscrypt.domain.model.CoinModel
import ru.iji.sunscrypt.domain.repository.Repository
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : Repository {

//    override val coins: Flow<List<CoinModel>> = api.coins.map {
//        it.map { coin ->
//            coin.toCoinModel()
//        }
//    }

//    override val coins: List<CoinModel> = api.coins.map {
//        it.toCoinModel()
//    }

//    override suspend fun getCoins(): List<CoinModel> = api.getCoins().map {
//        it.toCoinModel()
//    }

    override fun getCoins(): Flow<Resource<List<CoinModel>>> = flow {
        try {
            emit(Resource.Loading())
//            val coins = repository.getCoins()
            emit(Resource.Success(
                api.getCoins().map {
                    it.toCoinModel()
                }
            ))
        } catch (@Inject e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error(""))
        }
    }
}