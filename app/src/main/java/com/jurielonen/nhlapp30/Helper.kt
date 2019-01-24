package com.jurielonen.nhlapp30

import android.widget.ImageView
import com.jurielonen.nhlapp30.schedule.api.*
import com.jurielonen.nhlapp30.schedule.fragments.model.GameGoalie
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlayer
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlays
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ListGoaliePlayerHeader
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ListPlayerHeader
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ListPlaysHeader
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ViewType

object Helper {

    fun chooseImage(team: String): Int {
        return when (team) {
            "Anaheim Ducks" -> R.drawable.logo_ana

            "Arizona Coyotes" -> R.drawable.logo_ari

            "Boston Bruins" -> R.drawable.logo_bos

            "Buffalo Sabres" -> R.drawable.logo_buf

            "Carolina Hurricanes" -> R.drawable.logo_car

            "Columbus Blue Jackets" -> R.drawable.logo_cbj

            "Calgary Flames" -> R.drawable.logo_cgy

            "Chicago Blackhawks" -> R.drawable.logo_chi

            "Colorado Avalanche" -> R.drawable.logo_col

            "Dallas Stars" -> R.drawable.logo_dal

            "Detroit Red Wings" -> R.drawable.logo_det

            "Edmonton Oilers" -> R.drawable.logo_edm

            "Florida Panthers" -> R.drawable.logo_fla

            "Los Angeles Kings" -> R.drawable.logo_la

            "Minnesota Wild" -> R.drawable.logo_min

            "Montréal Canadiens" -> R.drawable.logo_mtl

            "Nashville Predators" -> R.drawable.logo_nas

            "New Jersey Devils" -> R.drawable.logo_nj

            "New York Islanders" -> R.drawable.logo_nyi

            "New York Rangers" -> R.drawable.logo_nyr

            "Ottawa Senators" -> R.drawable.logo_ott

            "Philadelphia Flyers" -> R.drawable.logo_phi

            "Pittsburgh Penguins" -> R.drawable.logo_pit

            "San Jose Sharks" -> R.drawable.logo_sj

            "St. Louis Blues" -> R.drawable.logo_stl

            "Tampa Bay Lightning" -> R.drawable.logo_tb

            "Toronto Maple Leafs" -> R.drawable.logo_tor

            "Vancouver Canucks" -> R.drawable.logo_van

            "Vegas Golden Knights" -> R.drawable.logo_vgk

            "Washington Capitals" -> R.drawable.logo_was

            "Winnipeg Jets" -> R.drawable.logo_wpg

            else -> R.drawable.right_arrow
        }
    }

    fun parsePlays(plays: ResponsePlays, homeTeam: String): List<ViewType>{
        val allPlays = plays.allPlays
        val playsToGet = (plays.scoringPlays + plays.penaltyPlays)
        playsToGet.sortedDescending()

        return formatPlays(playsToGet.map {
            val x = allPlays[it]
            GamePlays(x.team.name == homeTeam, x.team.name ,x.result.event, x.result.description, x.result.penaltyMinutes, x.about.period, x.about.periodTime)
        })
    }

    private fun formatPlays(plays: List<GamePlays>): List<ViewType>{
        val a = plays as ArrayList
        a.add(GamePlays(false, "", "START", "", 0, 1, "00:00"))
        a.add(GamePlays(false, "", "START", "", 0, 2, "00:00"))
        a.add(GamePlays(false, "", "START", "", 0, 3, "00:00"))

        return a.sortedWith(compareBy{it.time}).map {
            when(it.event){
                "START" -> ListPlaysHeader(it.time)
                else -> it
            } }
    }

    fun parseTeamsPlayers(teams: ResponseTeams): List<ViewType>{
        return Helper.formatPlayers(parsePlayers(teams.home), parseGoalies(teams.home), parsePlayers(teams.away), parseGoalies(teams.away))
    }

    private fun parsePlayers(team: ResponseTeam): List<GamePlayer> {
        val allPlayers = team.players
        return (allPlayers.filterValues {it.stats.skaterStats != null}).map {
            GamePlayer(it.value.person.id, it.value.person.fullName, it.value.position.code, it.value.position.name, it.value.stats.skaterStats)
        }
    }

    private fun parseGoalies(team: ResponseTeam): List<GameGoalie> {
        val allGoalies = team.players
        return (allGoalies.filterValues { it.stats.goalieStats != null }).map{
            GameGoalie(it.value.person.id, it.value.person.fullName, it.value.position.code, it.value.position.name, it.value.stats.goalieStats)
        }


    }

    private fun formatPlayers(homePlayers: List<GamePlayer>, homeGoalies: List<GameGoalie>, awayPlayers: List<GamePlayer>, awayGoalies: List<GameGoalie>): List<ViewType>{
        val a = ArrayList<ViewType>()
        a.add(ListPlayerHeader())
        a.addAll(homePlayers)
        a.add(ListGoaliePlayerHeader())
        a.addAll(homeGoalies)
        a.add(ListPlayerHeader())
        a.addAll(awayPlayers)
        a.add(ListGoaliePlayerHeader())
        a.addAll(awayGoalies)
        return a
    }

    fun parseStats(home: ResponseGameTeamSkaterStats, away: ResponseGameTeamSkaterStats): List<Stats>{

        return listOf(Stats(home.goals.toString(), away.goals.toString(), "Goals"),
            Stats(home.pim.toString(), away.pim.toString(), "Penalty Min"),
            Stats(home.shots.toString(), away.shots.toString(), "Shots"),
            Stats(home.powerPlayPercentage.toString(), away.powerPlayPercentage.toString(), "Power Play Percentage"),
            Stats(home.powerPlayGoals.toString(), away.powerPlayGoals.toString(), "Power Play Goals"),
            Stats(home.powerPlayOpportunities.toString(), away.powerPlayOpportunities.toString(), "Power Play Oportunities"),
            Stats(home.faceOffWinPercentage.toString(), away.faceOffWinPercentage.toString(), "Face Off Win Percentage"),
            Stats(home.blocked.toString(), away.blocked.toString(), "Blocked Shots"),
            Stats(home.takeaways.toString(), away.takeaways.toString(), "Takeaways"),
            Stats(home.giveaways.toString(), away.giveaways.toString(), "Giveaways"),
            Stats(home.hits.toString(), away.hits.toString(), "Hits"))
    }

    fun parsePreviewStats(home: Map<String, String>, away: Map<String, String>, stats: List<String>): List<Stats>{
        return stats.mapIndexed { index, s ->
            Stats(home.values.elementAt(index).toString(), away.values.elementAt(index).toString(), s)
        }
    }

    fun parseTeamLeaders(home: List<PreviewResponseLeaders>, away: List<PreviewResponseLeaders>): List<PreviewResponseLeaders>{
        val x = home.mapIndexed { index, it ->
            PreviewResponseLeaders(it.leaderCategory, it.leaders + away[index].leaders)
        }

        return x.map {
            PreviewResponseLeaders(it.leaderCategory, it.leaders.sortedWith(compareByDescending{ it.value.toInt()}))
        }
    }
}