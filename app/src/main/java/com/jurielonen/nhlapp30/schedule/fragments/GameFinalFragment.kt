package com.jurielonen.nhlapp30.schedule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jurielonen.nhlapp30.databinding.GameFinalFragmentBinding

class GameFinalFragment: Fragment() {

    private var gamePK = 0
    private var urlRecap = ""
    private var urlExtended = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: GameFinalFragmentBinding = GameFinalFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamePK = arguments!!.getInt("SELECTED_GAME", 0)
        urlRecap = arguments!!.getString("SELECTED_RECAP", "")
        urlExtended = arguments!!.getString("SELECTED_EXTENDED", "")

    }
}