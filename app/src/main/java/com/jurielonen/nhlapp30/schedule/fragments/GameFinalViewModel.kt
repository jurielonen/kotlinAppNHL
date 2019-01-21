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
    private val scheduleResult = Transformations.map(gameID) {
        repository.search(it)
    }

    val gameData: LiveData<PagedList<GameData>> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("GameViewModel", "list: ${it.data.value} ")
        it.data }

    val networkErrors: LiveData<String> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("GameViewModel", "list: ${it.data.value} ")
        it.networkErrors }


    fun search(id: String){
        gameID.postValue(id)

    }
}