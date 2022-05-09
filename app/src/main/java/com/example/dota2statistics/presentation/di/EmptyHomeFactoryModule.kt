package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import com.example.dota2statistics.presentation.viewmodels.EmptyHomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EmptyHomeFactoryModule {

    @Singleton
    @Provides
    fun providesEmptyHomeFactory(
        getPlayerByIDUseCase: GetPlayerByIDUseCase,
        getPlayersByPersonaNameUseCase: GetPlayersByPersonaNameUseCase
    ): EmptyHomeViewModelFactory {
        return EmptyHomeViewModelFactory(getPlayerByIDUseCase, getPlayersByPersonaNameUseCase)
    }
}