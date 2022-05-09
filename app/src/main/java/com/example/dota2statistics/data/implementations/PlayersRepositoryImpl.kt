package com.example.dota2statistics.data.implementations

import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.data.models.byPersonaName.PlayersByPersonaName
import com.example.dota2statistics.data.repository.dataSource.DotaLocalDataSource
import com.example.dota2statistics.data.repository.dataSource.DotaRemoteDataSource
import com.example.dota2statistics.domain.repository.PlayersRepository

class PlayersRepositoryImpl(
    private val dotaRemoteDataSource: DotaRemoteDataSource,
    private val dotaLocalDataSource: DotaLocalDataSource
) : PlayersRepository {

    override suspend fun getPlayersByPersonaName(playerName: String): List<PlayerByPersonaNameItem>? {
        return getAndParsePlayerFromAPI(playerName)
    }

    private suspend fun getAndParsePlayerFromAPI(playerName: String): List<PlayerByPersonaNameItem>? {
        val response = dotaRemoteDataSource.getPlayerByPersonaName(playerName)
        return response.body()?.let {
            it
        }
    }

    override suspend fun getPlayerByID(playerID: Long): Profile? {
        return getPlayerByIDFromAPI(playerID)
    }

    private suspend fun getPlayerByIDFromAPI(playerID: Long): Profile? {
        val response = dotaRemoteDataSource.getPlayerByID(playerID)
        return response.body()?.let {
            it.profile
        }
    }

    override suspend fun savePlayerToDB(profile: Profile) {
        TODO("Not yet implemented")
    }

    override fun getPlayersFromDB(): Profile {
        TODO("Not yet implemented")
    }
}