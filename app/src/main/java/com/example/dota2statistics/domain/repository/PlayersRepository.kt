package com.example.dota2statistics.domain.repository

import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem

interface PlayersRepository {

    suspend fun getPlayersByPersonaName(playerName: String): List<PlayerByPersonaNameItem>?
    suspend fun getPlayerByID(playerID: Int): Profile?
    suspend fun savePlayerToDB(profile: Profile)
    fun getPlayersFromDB(): Profile?
}