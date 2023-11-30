package ru.iji.sunscrypt.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.iji.sunscrypt.domain.repository.Repository
import ru.iji.sunscrypt.domain.use_cases.Coins

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideCoins(repository: Repository): Coins {
        return Coins(repository = repository)
    }
}