package com.jurielonen.nhlapp30.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jurielonen.nhlapp30.databinding.PlayerFragmentBinding

class PlayerFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = PlayerFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}