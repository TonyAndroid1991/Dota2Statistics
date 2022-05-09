package com.example.dota2statistics.data.repository.dataSource

import com.example.dota2statistics.data.models.byID.Profile
import kotlinx.coroutines.flow.Flow

interface DotaLocalDataSource {

    suspend fun savePlayerToDB(profile: Profile)
    fun getPlayersFromDB(): Flow<List<Profile>>
}