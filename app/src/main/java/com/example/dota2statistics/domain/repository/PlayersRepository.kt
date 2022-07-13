package com.example.dota2statistics.domain.repository

import Resource
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem

interface PlayersRepository {

    suspend fun getPlayersByPersonaName(playerName: String): Resource<List<PlayerByPersonaNameItem>>
    suspend fun getPlayerByID(playerID: Int): Resource<Profile>
    suspend fun savePlayerToDB(profile: Profile)
    fun getPlayersFromDB(): Profile
}