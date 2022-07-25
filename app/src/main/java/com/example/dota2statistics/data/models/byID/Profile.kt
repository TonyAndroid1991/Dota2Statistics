package com.example.dota2statistics.data.models.byID


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val accountId: Int,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("avatarfull")
    val avatarFull: String,
    @SerializedName("avatarmedium")
    val avatarMedium: String,
    @SerializedName("cheese")
    val cheese: Int,
    @SerializedName("personaname")
    val personaName: String,
    @SerializedName("plus")
    val plus: Boolean,
    @SerializedName("profileurl")
    val profileUrl: String,
    @SerializedName("steamid")
    val steamId: String
) : Serializable