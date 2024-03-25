package ru.iji.sunscrypt.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.iji.sunscrypt.domain.repository.CoinsRepository
import ru.iji.sunscrypt.domain.usecases.GetCoins

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideCoins(coinsRepository: CoinsRepository): GetCoins {
        return GetCoins(coinsRepository = coinsRepository)
    }
}