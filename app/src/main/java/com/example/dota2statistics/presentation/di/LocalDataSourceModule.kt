package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.data.db.DotaDAO
import com.example.dota2statistics.data.repository.dataSource.DotaLocalDataSource
import com.example.dota2statistics.data.repository.datasourcesImpl.DotaLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun providesDotaLocalDataSource(dotaDAO: DotaDAO): DotaLocalDataSource {
        return DotaLocalDataSourceImpl(dotaDAO)
    }
}