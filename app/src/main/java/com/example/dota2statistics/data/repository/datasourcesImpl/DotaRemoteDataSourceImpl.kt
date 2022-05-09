package com.example.dota2statistics.data.repository.datasourcesImpl

import com.example.dota2statistics.data.api.ApiService
import com.example.dota2statistics.data.models.byID.PlayerByID
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.data.repository.dataSource.DotaRemoteDataSource
import retrofit2.Response

class DotaRemoteDataSourceImpl(private val apiService: ApiService) : DotaRemoteDataSource {


    override suspend fun getPlayerByPersonaName(playerName: String): Response<List<PlayerByPersonaNameItem>> {
        return apiService.getPlayerByPersonaName(playerName)
    }

    override suspend fun getPlayerByID(playerID: Long): Response<PlayerByID> {
        return apiService.getPlayerByAccountId(playerID)
    }


}