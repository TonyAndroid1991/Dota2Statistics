package com.example.dota2statistics.data.api

import com.example.dota2statistics.BuildConfig
import com.example.dota2statistics.data.models.byID.PlayerByID
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getPlayerByPersonaName(
        @Query("q") q: String,
        @Query("api_key") apiKey: String = BuildConfig. API_KEY
    ): Response<List<PlayerByPersonaNameItem>>

    @GET("players/{account_id}")
    suspend fun getPlayerByAccountId(
        @Path("account_id") accountId: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<PlayerByID>


}