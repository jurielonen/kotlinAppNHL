package com.jurielonen.nhlapp30.schedule.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jurielonen.nhlapp30.schedule.data.GameRepository
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData

class GameFinalViewModel(private val repository: GameRepository): ViewModel() {

    private val gameID = MutableLiveData<String>()
    private val gameResult = Transformations.map(gameID) {
        repository.search(it)
    }

    val gameData: LiveData<GameData> = Transformations.switchMap(gameResult
    ) { it ->
        Log.d("GameViewModel", "gamedata: ${it.data.value} ")
        it.data }

    val networkErrors: LiveData<String> = Transformations.switchMap(gameResult
    ) { it ->
        Log.d("GameViewModel", "networkerror: ${it.data.value} ")
        it.networkErrors }

    val isRequestInProgress: LiveData<Boolean> = Transformations.switchMap(gameResult){it ->
        Log.d("GameViewModel", "request: ${it.data.value} ")
        it.isInProgress
    }

    fun search(id: String){
        gameID.postValue(id)

    }
}