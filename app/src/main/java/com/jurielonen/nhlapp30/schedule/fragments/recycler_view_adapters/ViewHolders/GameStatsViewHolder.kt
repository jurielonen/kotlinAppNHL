package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListStatsBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats

class GameStatsViewHolder(private val binding: GameListStatsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(stats: Stats){
        with(binding){
            stat = stats
            gameStatListProgressBar.max = 200
            progress = compareTwoValues(stats.home, stats.away)
            executePendingBindings()
        }
    }

    private fun compareTwoValues(home: String, away: String): Int {
        val re = Regex("[^0-9]")
        val reAlp = Regex("[^A-Za-z]")
        return if(home.contains(reAlp)){
            val homeInt = java.lang.Double.parseDouble(re.replace(away, ""))
            val awayInt = java.lang.Double.parseDouble(re.replace(home, ""))
            val dValue = homeInt / (homeInt + awayInt) * 2.0 * 100.0
            dValue.toInt()
        } else{
            val homeInt = java.lang.Double.parseDouble(home)
            val awayInt = java.lang.Double.parseDouble(away)
            val dValue = homeInt / (homeInt + awayInt) * 2.0 * 100.0
            dValue.toInt()
        }
    }
}