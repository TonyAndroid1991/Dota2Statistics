package com.example.dota2statistics.domain.useCases

import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.domain.repository.PlayersRepository

class GetPlayerByIDUseCase(private val playersRepository: PlayersRepository) {

    suspend fun getPlayerById(playerID: Int): Profile? {
        return playersRepository.getPlayerByID(playerID)
    }
}