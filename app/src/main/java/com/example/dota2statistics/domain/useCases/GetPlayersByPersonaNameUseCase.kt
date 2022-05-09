package com.example.dota2statistics.domain.useCases

import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.domain.repository.PlayersRepository

class GetPlayersByPersonaNameUseCase(private val playersRepository: PlayersRepository) {

    suspend fun getPlayersByName(playerName: String): List<PlayerByPersonaNameItem>? {
        return playersRepository.getPlayersByPersonaName(playerName)
    }
}