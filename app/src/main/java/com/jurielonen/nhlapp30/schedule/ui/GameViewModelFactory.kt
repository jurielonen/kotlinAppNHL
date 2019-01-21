package com.jurielonen.nhlapp30.schedule.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jurielonen.nhlapp30.schedule.data.GameRepository
import com.jurielonen.nhlapp30.schedule.fragments.GameFinalViewModel

class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameFinalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameFinalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}