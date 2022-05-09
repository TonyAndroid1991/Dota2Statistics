package com.example.dota2statistics.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dota2statistics.data.models.byID.Profile

@Database(
    entities = [Profile::class],
    version = 1,
    exportSchema = false
)

@TypeConverters()
abstract class ProfileDB : RoomDatabase() {
    abstract fun getDotaDAO(): DotaDAO
}