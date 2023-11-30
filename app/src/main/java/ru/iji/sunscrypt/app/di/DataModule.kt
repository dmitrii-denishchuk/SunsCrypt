package ru.iji.sunscrypt.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.iji.sunscrypt.common.Constants
import ru.iji.sunscrypt.data.api.CoinPaprikaApi
import ru.iji.sunscrypt.data.repository.RepositoryImpl
import ru.iji.sunscrypt.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CoinPaprikaApi): Repository {
        return RepositoryImpl(api = api)
    }
}