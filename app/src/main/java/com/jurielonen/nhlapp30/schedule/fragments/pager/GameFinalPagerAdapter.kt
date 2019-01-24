package com.jurielonen.nhlapp30.schedule.fragments.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData

class GameFinalPagerAdapter (fm: FragmentManager, private val headers: Array<String>, private val game: GameData) :
    FragmentStatePagerAdapter(fm) {

    private val ARG_OBJECT = "object"

    override fun getCount(): Int  = headers.size

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