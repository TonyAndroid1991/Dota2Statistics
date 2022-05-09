package com.example.dota2statistics.data.repository.dataSource

import com.example.dota2statistics.data.models.byID.PlayerByID
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import retrofit2.Response

interface DotaRemoteDataSource {

    suspend fun getPlayerByPersonaName(playerName: String): Response<List<PlayerByPersonaNameItem>>
    suspend fun getPlayerByID(playerID: Long): Response<PlayerByID>
}