package com.jurielonen.nhlapp30.schedule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jurielonen.nhlapp30.databinding.VideoFragmentBinding

class VideoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: VideoFragmentBinding = VideoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}