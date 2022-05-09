package com.example.dota2statistics.data.models.byID


import com.google.gson.annotations.SerializedName

data class PlayerByID(
    @SerializedName("competitive_rank")
    val competitiveRank: Any,
    @SerializedName("leaderboard_rank")
    val leaderboardRank: Any,
    @SerializedName("mmr_estimate")
    val mmrEstimate: MmrEstimate,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("rank_tier")
    val rankTier: Any,
    @SerializedName("solo_competitive_rank")
    val soloCompetitiveRank: Any,
    @SerializedName("tracked_until")
    val trackedUntil: Any
)