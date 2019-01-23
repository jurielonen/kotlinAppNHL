package com.jurielonen.nhlapp30.schedule.fragments.game_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData

class GameFinalPagerAdapter (fm: FragmentManager, private val count: Int, private val headers: Array<String>, private val game: GameData) :
    FragmentStatePagerAdapter(fm) {

    private val ARG_OBJECT = "object"

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        val fragment = GameFinalPagerFragment()
        fragment.setGame(game)
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, i + 1)

        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return headers[position].toUpperCase()
    }
}