package com.example.dota2statistics.data.models.byID


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(
    tableName = "profile"
)
@Parcelize
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val accountId: Int,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("avatarfull")
    val avatarfull: String,
    @SerializedName("avatarmedium")
    val avatarmedium: String,
    @SerializedName("cheese")
    val cheese: Int,
    @SerializedName("personaname")
    val personaname: String,
    @SerializedName("plus")
    val plus: Boolean,
    @SerializedName("profileurl")
    val profileurl: String,
    @SerializedName("steamid")
    val steamid: String
): Parcelable, Serializable