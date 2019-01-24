package com.jurielonen.nhlapp30.schedule.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jurielonen.nhlapp30.schedule.data.GameRepository

class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameFinalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameFinalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}