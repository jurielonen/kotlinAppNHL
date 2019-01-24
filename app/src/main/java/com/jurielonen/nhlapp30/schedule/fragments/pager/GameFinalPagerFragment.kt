package com.jurielonen.nhlapp30.schedule.fragments.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.GamePlayersAdapter
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.GamePlaysAdapter
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.GameStatAdapter
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ViewType

class GameFinalPagerFragment : Fragment() {

    private val ARG_OBJECT = "object"
    private lateinit var plays: List<ViewType>
    private lateinit var players: List<ViewType>
    private lateinit var stats: List<Stats>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.game_pager_item, container, false) as ViewGroup
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.gamePagerRecyclerView)
        val x = arguments?.getInt(ARG_OBJECT)
        when(x){
            1 -> {
                val adapter = GameStatAdapter()
                adapter.submitList(stats)
                recyclerView.adapter = adapter
                }
            2-> {
                recyclerView.adapter = GamePlaysAdapter(plays)
            }
            3->{
                recyclerView.adapter = GamePlayersAdapter(players)
            }

            else -> {
                recyclerView.adapter = GamePlaysAdapter(plays)
            }
        }

        return rootView
    }

    fun setGame(game: GameData){
        players = game.players
        plays = game.plays
        stats = game.stats

    }
}