package com.example.dota2statistics.data.implementations

import Resource
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.data.repository.dataSource.DotaLocalDataSource
import com.example.dota2statistics.data.repository.dataSource.DotaRemoteDataSource
import com.example.dota2statistics.domain.repository.PlayersRepository

class PlayersRepositoryImpl(
    private val dotaRemoteDataSource: DotaRemoteDataSource,
    private val dotaLocalDataSource: DotaLocalDataSource
) : PlayersRepository {

    override suspend fun getPlayersByPersonaName(playerName: String): Resource<List<PlayerByPersonaNameItem>> {
        return getAndParsePlayerFromAPI(playerName)
    }

    private suspend fun getAndParsePlayerFromAPI(playerName: String): Resource<List<PlayerByPersonaNameItem>> {
        val response = dotaRemoteDataSource.getPlayerByPersonaName(playerName)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getPlayerByID(playerID: Int): Resource<Profile> {
        return getPlayerByIDFromAPI(playerID)
    }

    private suspend fun getPlayerByIDFromAPI(playerID: Int): Resource<Profile> {
        val response = dotaRemoteDataSource.getPlayerByID(playerID)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it.profile)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun savePlayerToDB(profile: Profile) {
        TODO("Not yet implemented")
    }

    override fun getPlayersFromDB(): Profile {
        TODO("Not yet implemented")
    }
}