package com.jurielonen.nhlapp30.schedule.api

data class GamePreviewResponse (
    val teams: List<PreviewResponseTeam>
)

data class PreviewResponseTeam(
    val id: Int,
    val name: String,
    val teamStats: List<PreviewResponseStats>,
    val teamLeaders: List<PreviewResponseLeaders>
)

data class PreviewResponseStats(
    val splits: List<PreviewResponseStatsSplits>
)

data class PreviewResponseStatsSplits(
    val stat: Map<String, String>
)

data class PreviewResponseLeaders(
    val leaderCategory: String,
    val leaders: List<PreviewResponseLeadersPlayers>
)

data class PreviewResponseLeadersPlayers(
    val rank: Int,
    val value: String,
    val person: PreviewResponseLeadersPerson,
    val team: PreviewResponseLeaderTeam
)

data class PreviewResponseLeaderTeam(
    val name: String
)

data class PreviewResponseLeadersPerson(
    val fullName: String,
    val id: Int
)
/*
data class PreviewResponseStatsSplitsStats(
    val gamesPlayed: Int? = 0,
    val wins: Int? = 0,
    val losses: Int? = 0,
    val ot: Int? = 0,
    val pts: Int? = 0,
    val ptPctg: String? = "",
    val goalsPerGame: Double? = 0.0,
    val goalsAgainstPerGame: Double? = 0.0,
    val evGGARatio: Double? = 0.0,
    val powerPlayPercentage: String? = "",
    val powerPlayGoals: Int? = 0,
    val powerPlayGoalsAgainst: Int? = 0,
    val powerPlayOpportunities: Int? = 0,
    val penaltyKillPercentage: String? = "",
    val shotsPerGame: Double? = 0.0,
    val shotsAllowed: Double? = 0.0,
    val winScoreFirst: Double? = 0.0,
    val winOppScoreFirst: Double? = 0.0,
    val winLeadFirstPer: Double? = 0.0,
    val winLeadSecondPer: Double? = 0.0,
    val winOutshootOpp: Double? = 0.0,
    val winOutshotByOpp: Double? = 0.0,
    val faceOffsTaken: Int? = 0,
    val faceOffsWon: Int? = 0,
    val faceOffsLost: Int? = 0,
    val faceOffWinPercentage: String? = "",
    val shootingPctg: Double? = 0.0,
    val savePctg: Double? = 0.0,

)

    val wins: String? = "",
    val losses: String? = "",
    val ot: String? = "",
    val pts: String? = "",
    val ptPctg: String? = "",
    val goalsPerGame: String? = "",
    val goalsAgainstPerGame: String? = "",
    val evGGARatio: String? = "",
    val powerPlayPercentage: String? = "",
    val powerPlayGoals: String? = "",
    val powerPlayGoalsAgainst: String? = "",
    val powerPlayOpportunities: String? = "",
    val penaltyKillOpportunities: String? = "",
    val penaltyKillPercentage: String? = "",
    val shotsPerGame: String? = "",
    val shotsAllowed: String? = "",
    val winScoreFirst: String? = "",
    val winOppScoreFirst: String? = "",
    val winLeadFirstPer: String? = "",
    val winLeadSecondPer: String? = "",
    val winOutshootOpp: String? = "",
    val winOutshotByOpp: String? = "",
    val faceOffsTaken: String? = "",
    val faceOffsWon: String? = "",
    val faceOffsLost: String? = "",
    val faceOffWinPercentage: String? = "",
    val savePctRank: String? = "",
    val shootingPctRank: String? = ""
 */