package com.example.dota2statistics.data.implementations

import Resource
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.domain.repository.PlayersRepository

class PlayersRepositoryDouble : PlayersRepository {

    override suspend fun getPlayersByPersonaName(playerName: String): Resource<List<PlayerByPersonaNameItem>> {
        return Resource.Success(
            listOf(
                PlayerByPersonaNameItem(1111, "https", "", playerName, 0.0),
                PlayerByPersonaNameItem(2222, "https", "", playerName.plus("1"), 0.0),
                PlayerByPersonaNameItem(3333, "https", "", playerName.plus("2"), 0.0)
            )
        )
    }

    override suspend fun getPlayerByID(playerID: Int): Resource<Profile> {
        return Resource.Success(
            Profile(
                playerID as Int,
                "myAvatar",
                "",
                "",
                1111,
                "",
                false,
                "",
                ""
            )
        )
    }

    override suspend fun savePlayerToDB(profile: Profile) {
        TODO("Not yet implemented")
    }

    override fun getPlayersFromDB(): Profile {
        TODO("Not yet implemented")
    }
}
