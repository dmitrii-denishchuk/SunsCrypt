package ru.iji.sunscrypt.app.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.iji.sunscrypt.data.datasource.remote.RemoteDataSource
import ru.iji.sunscrypt.data.datasource.remote.RemoteDataSourceImpl
import ru.iji.sunscrypt.data.repository.CoinsRepositoryImpl
import ru.iji.sunscrypt.data.service.CoinPaprika
import ru.iji.sunscrypt.domain.repository.CoinsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(moshi: Moshi): CoinPaprika {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinPaprika::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: CoinPaprika): RemoteDataSource {
        return RemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideCoinsRepository(remoteDataSource: RemoteDataSource): CoinsRepository {
        return CoinsRepositoryImpl(remoteDataSource = remoteDataSource)
    }
}