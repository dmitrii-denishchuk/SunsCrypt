package ru.iji.sunscrypt.domain.usecases

import ru.iji.sunscrypt.domain.repository.CoinsRepository
import javax.inject.Inject

class GetCoins @Inject constructor(private val coinsRepository: CoinsRepository) {

    suspend operator fun invoke() = coinsRepository.getCoins()
}