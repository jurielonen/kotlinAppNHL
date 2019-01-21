package com.jurielonen.nhlapp30.schedule.api

import com.google.gson.annotations.SerializedName
import com.jurielonen.nhlapp30.schedule.model.Status
import com.jurielonen.nhlapp30.schedule.model.Teams
import com.jurielonen.nhlapp30.schedule.model.Venue


class ScheduleResponse(@SerializedName("dates") val dates: List<Schedule>)

class Schedule(
    @SerializedName("date") val date: String,
    @SerializedName("games") val games: List<GameContent>
)

class GameContent(
    val gamePk: Int,
    val link: String,
    val gameDate: String,
    val status: Status,
    val teams: Teams,
    val venue: Venue,
    val content: Content
)
class Content(
    val media: Media
)

class Media(
    val epg: List<Epg>
)

class Epg(
    val title: String,
    val items: List<EpgItems>?
)

class EpgItems(
    val playbacks: List<Playbacks>?
)

class Playbacks(
    val url: String?
)

data class Urls( val recap: String, val extended: String)