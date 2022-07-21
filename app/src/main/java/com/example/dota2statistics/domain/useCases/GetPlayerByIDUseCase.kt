package com.example.dota2statistics.domain.useCases

import Resource
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.domain.repository.PlayersRepository

class GetPlayerByIDUseCase(private val playersRepository: PlayersRepository) {

    suspend fun getPlayerById(playerID: Int): Resource<Profile> {
        return playersRepository.getPlayerByID(playerID)
    }
}