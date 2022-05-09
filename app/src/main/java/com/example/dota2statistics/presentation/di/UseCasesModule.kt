package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.domain.repository.PlayersRepository
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Singleton
    @Provides
    fun providesGetPlayersByPersonaNameUseCase(playersRepository: PlayersRepository): GetPlayersByPersonaNameUseCase {
        return GetPlayersByPersonaNameUseCase(playersRepository)
    }

    @Singleton
    @Provides
    fun providesGetPlayerByIDUseCase(playersRepository: PlayersRepository): GetPlayerByIDUseCase {
        return GetPlayerByIDUseCase(playersRepository)
    }
}