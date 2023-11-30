package ru.iji.sunscrypt.data.mapper

import ru.iji.sunscrypt.data.dto.CoinDto
import ru.iji.sunscrypt.domain.model.CoinModel

object CoinMapper {

    fun CoinDto.toCoinModel() = CoinModel(
        symbol = symbol,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        id = id,
        type = type
    )

//    fun CoinModel.toCoinDto() = CoinDto(
//        symbol = symbol,
//        isActive = isActive,
//        isNew = isNew,
//        name = name,
//        rank = rank,
//        id = id,
//        type = type
//    )
}