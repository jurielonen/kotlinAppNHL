package com.jurielonen.nhlapp30.schedule.fragments.game_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GameFinalPagerAdapter (fm: FragmentManager, private val count: Int, private val headers: Array<String>) :
    FragmentStatePagerAdapter(fm) {

    private val ARG_OBJECT = "object"

    override fun getCount(): Int  = count

    override fun getItem(i: Int): Fragment {
        val fragment = GameFinalPagerFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, i + 1)

        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return headers[position].toUpperCase()
    }
}