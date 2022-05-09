package com.example.dota2statistics.domain.useCases

import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.domain.repository.PlayersRepository

class SavePlayerToDBUseCase(playersRepository: PlayersRepository) {

    suspend fun savePlayerToDB(profile: Profile) {

    }
}