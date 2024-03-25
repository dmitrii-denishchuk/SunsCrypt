package ru.iji.sunscrypt.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.iji.sunscrypt.data.dto.CoinDto

interface CoinPaprika {

    @GET("coins/{id}")
    suspend fun getCoin(@Path("id") id: String): CoinDto

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>
}