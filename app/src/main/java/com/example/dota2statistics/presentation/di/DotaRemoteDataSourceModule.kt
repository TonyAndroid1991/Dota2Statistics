package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.data.api.ApiService
import com.example.dota2statistics.data.repository.dataSource.DotaRemoteDataSource
import com.example.dota2statistics.data.repository.datasourcesImpl.DotaRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DotaRemoteDataSourceModule {

    @Provides
    @Singleton
    fun providesDotaRemoteDataSource(apiService: ApiService): DotaRemoteDataSource{
        return DotaRemoteDataSourceImpl(apiService)
    }
}