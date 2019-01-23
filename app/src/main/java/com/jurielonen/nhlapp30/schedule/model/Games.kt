package com.jurielonen.nhlapp30.schedule.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "games")
data class Games(
    @PrimaryKey
    val gamePk: Int,
    val link: String,
    val gameDate: String,
    @Embedded
    val status: Status,
    @Embedded
    val teams: Teams,
    @Embedded
    val venue: Venue
    ){
    var date: String = ""
    var urlRecap: String = ""
    var urlExtended: String = ""


    fun getState(): String{
        return when(status.codedGameState){
            1->"PREVIEW"
            3->"LIVE"
            7->"FINAL"
            else->"UNDEFIED"
        }
    }

    fun homeStat(): String{
        return when(status.codedGameState){
            1->"${teams.home.leagueRecord.wins}-${teams.home.leagueRecord.losses}-${teams.home.leagueRecord.ot}"
            3->"${teams.home.score}"
            7->"${teams.home.score}"
            else->"${teams.home.score}"
        }
    }

    fun awayStat(): String{
        return when(status.codedGameState){
            1->"${teams.away.leagueRecord.wins}-${teams.away.leagueRecord.losses}-${teams.away.leagueRecord.ot}"
            3->"${teams.away.score}"
            7->"${teams.away.score}"
            else->"${teams.away.score}"
        }
    }
}

data class Status(
    val codedGameState: Int? = 0,
    val detailedState: String? = ""
)

data class Teams(
    @Embedded(prefix = "home_")
    val home: Team,
    @Embedded(prefix = "away_")
    val away: Team
)

data class Team(
    @Embedded(prefix = "record_")
    val leagueRecord: LeagueRecord,
    val score: Int,
    @Embedded(prefix = "details_")
    @SerializedName("team")
    val details: TeamDetails
)

data class LeagueRecord(
    val wins: Int,
    val losses: Int,
    val ot: Int
)

data class TeamDetails(
    val id: Int,
    val name: String,
    val link: String
)

data class Venue(
    val name: String? = ""
)