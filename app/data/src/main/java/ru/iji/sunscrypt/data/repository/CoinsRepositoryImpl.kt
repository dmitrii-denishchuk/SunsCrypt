package ru.iji.sunscrypt.data.repository

import ru.iji.sunscrypt.data.datasource.remote.RemoteDataSource
import ru.iji.sunscrypt.domain.repository.CoinsRepository
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CoinsRepository {

    override suspend fun getCoin(id: String) = remoteDataSource.getCoin(id)

    override suspend fun getCoins() = remoteDataSource.getCoins()
}