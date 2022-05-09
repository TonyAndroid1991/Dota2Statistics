package com.example.dota2statistics.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.dota2statistics.data.db.DotaDAO
import com.example.dota2statistics.data.db.ProfileDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun providesDataBase(app: Application): ProfileDB {
        return Room.databaseBuilder(app, ProfileDB::class.java, "profile_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesDotaDAO(profileDB: ProfileDB): DotaDAO {
        return profileDB.getDotaDAO()
    }

}