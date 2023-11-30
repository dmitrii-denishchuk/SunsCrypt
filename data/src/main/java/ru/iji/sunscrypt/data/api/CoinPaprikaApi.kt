package ru.iji.sunscrypt.data.api

import retrofit2.http.GET
import ru.iji.sunscrypt.data.dto.CoinDto

interface CoinPaprikaApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

//    @get:GET("coins")
//    val coins: List<CoinDto>
}