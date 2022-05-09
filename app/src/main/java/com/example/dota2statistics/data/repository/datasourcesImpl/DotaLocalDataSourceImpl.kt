package com.example.dota2statistics.data.repository.datasourcesImpl

import com.example.dota2statistics.data.db.DotaDAO
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.repository.dataSource.DotaLocalDataSource
import kotlinx.coroutines.flow.Flow

class DotaLocalDataSourceImpl(private val dotaDAO: DotaDAO) : DotaLocalDataSource {

    override suspend fun savePlayerToDB(profile: Profile) {
        dotaDAO.insert(profile)
    }

    override fun getPlayersFromDB(): Flow<List<Profile>> {
       return dotaDAO.getPlayersFromDB()
    }

}