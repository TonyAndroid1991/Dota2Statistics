package com.example.dota2statistics.data.models.byPersonaName


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayerByPersonaNameItem(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("avatarfull")
    val avatarfull: String,
    @SerializedName("last_match_time")
    val lastMatchTime: String,
    @SerializedName("personaname")
    val personaname: String,
    @SerializedName("similarity")
    val similarity: Double
) : Serializable