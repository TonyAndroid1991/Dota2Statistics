package com.example.dota2statistics.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dota2statistics.data.models.byID.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface DotaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: Profile)

    @Query("SELECT * FROM profile")
    fun getPlayersFromDB(): Flow<List<Profile>>
}