package ru.iji.sunscrypt.domain.usecases

import ru.iji.sunscrypt.domain.repository.CoinsRepository
import javax.inject.Inject

class GetCoin @Inject constructor(private val coinsRepository: CoinsRepository) {

    suspend operator fun invoke(id: String) = coinsRepository.getCoin(id)
}