package com.jurielonen.nhlapp30.schedule.fragments.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.api.PreviewResponseLeaders
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewData
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.GameLeadersExpList
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.GameStatAdapter

class GamePreviewPagerFragment : Fragment() {

    private val ARG_OBJECT = "object"
    private lateinit var leaders: List<PreviewResponseLeaders>
    private lateinit var stats: List<Stats>
    private lateinit var ranks: List<Stats>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val x = arguments?.getInt(ARG_OBJECT)
        val rootView: ViewGroup
        when(x){
            1 -> {
                rootView = inflater.inflate(R.layout.game_pager_item, container, false) as ViewGroup
                val recyclerView = rootView.findViewById(R.id.gamePagerRecyclerView) as RecyclerView
                val adapter = GameStatAdapter()
                adapter.submitList(stats)
                recyclerView.adapter = adapter
            }
            2 -> {
                rootView = inflater.inflate(R.layout.game_pager_item, container, false) as ViewGroup
                val recyclerView = rootView.findViewById(R.id.gamePagerRecyclerView) as RecyclerView
                val adapter = GameStatAdapter()
                adapter.submitList(ranks)
                recyclerView.adapter = adapter
            }
            else -> {
                rootView = inflater.inflate(R.layout.game_pager_item_exp_list, container, false) as ViewGroup
                val expList = rootView.findViewById(R.id.gamePagerExpListView) as ExpandableListView
                expList.setAdapter(GameLeadersExpList(leaders))
            }
        }

        return rootView
    }

    fun setGame(game: GamePreviewData){
        leaders = game.teamLeaders
        ranks = game.teamRanks
        stats = game.teamStats

    }
}