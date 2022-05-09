package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.data.implementations.PlayersRepositoryImpl
import com.example.dota2statistics.data.repository.dataSource.DotaLocalDataSource
import com.example.dota2statistics.data.repository.dataSource.DotaRemoteDataSource
import com.example.dota2statistics.domain.repository.PlayersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlayersRepositoryModule {

    @Singleton
    @Provides
    fun providesPlayersRepository(
        dotaRemoteDataSource: DotaRemoteDataSource,
        dotaLocalDataSource: DotaLocalDataSource
    ): PlayersRepository {
        return PlayersRepositoryImpl(dotaRemoteDataSource, dotaLocalDataSource)
    }
}