package com.jurielonen.nhlapp30.schedule.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jurielonen.nhlapp30.schedule.data.GamePreviewGetter

class GamePreviewViewModelFactory(private val getter: GamePreviewGetter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamePreviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GamePreviewViewModel(getter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}