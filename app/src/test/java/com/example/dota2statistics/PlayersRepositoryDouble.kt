package com.example.dota2statistics

import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.domain.repository.PlayersRepository

class PlayersRepositoryTestDouble : PlayersRepository {

    override suspend fun getPlayersByPersonaName(playerName: String): List<PlayerByPersonaNameItem>? {
        val playerByPersonaNameItem = PlayerByPersonaNameItem(1111, "https", "", playerName, 0.0)
        return listOf(playerByPersonaNameItem)
    }

    override suspend fun getPlayerByID(playerID: Int): Profile? {
        return Profile(playerID as Int, "myAvatar", "", "", 1111, "", false, "", "")
    }

    override suspend fun savePlayerToDB(profile: Profile) {
        TODO("Not yet implemented")
    }

    override fun getPlayersFromDB(): Profile? {
        TODO("Not yet implemented")
    }


}