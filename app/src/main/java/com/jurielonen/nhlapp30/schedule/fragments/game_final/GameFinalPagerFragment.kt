package com.jurielonen.nhlapp30.schedule.fragments.game_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.jurielonen.nhlapp30.R

class GameFinalPagerFragment : Fragment() {

    private val ARG_OBJECT = "object"

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val rootView = inflater.inflate(R.layout.game_pager_item, container, false) as ViewGroup
        val recyclerView = rootView.findViewById<ListView>(R.id.gamePagerRecyclerView)
        val x = arguments?.getInt(ARG_OBJECT)
        when(x){
            1 -> {
                //listView.adapter = GameStatAdapter(context!!, gameHolder.statsArrayList)
            }
            2-> {
                //listView.adapter = GamePlayAdapter(context!!, gameHolder.playsArrayList)
            }
            3->{
                //listView.adapter = GamePlayerAdapter(context!!, gameHolder.playerStatsArrayList)
            }

            else -> {
                //listView.adapter = GameStatAdapter(context!!, gameHolder.statsArrayList)
            }
        }

        return rootView
    }
}