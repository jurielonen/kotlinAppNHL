package com.jurielonen.nhlapp30.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jurielonen.nhlapp30.databinding.FragmentStandingsBinding

class StandingsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStandingsBinding.inflate(inflater, container, false)

        return binding.root
        //TODO: MAKE STANDINGS VIEW
    }
}