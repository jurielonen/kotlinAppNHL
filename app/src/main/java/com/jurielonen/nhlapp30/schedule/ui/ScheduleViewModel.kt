package com.jurielonen.nhlapp30.schedule.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jurielonen.nhlapp30.schedule.model.Games
import com.jurielonen.nhlapp30.schedule.data.ScheduleRepository

class ScheduleViewModel(private val repository: ScheduleRepository): ViewModel() {


    private val queryLiveData = MutableLiveData<String>()
    private val scheduleResult = Transformations.map(queryLiveData) {
        if(it[0] == 'R'){
            queryLiveData.value = it.replace("R", "")
            repository.refresh(it.replace("R", ""))
        }
        else
        repository.search(it)
    }

    val schedule: LiveData<List<Games>> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("ScheduleViewModel", "list: ${it.data.value} ")
        it.data }

    val networkErrors: LiveData<String> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("ScheduleViewModel", "networkerrors: ${it.networkErrors} ")
        it.networkErrors }

    val isRequestInProgress: LiveData<Boolean> = Transformations.switchMap(scheduleResult){
        it ->
        Log.d("ScheduleViewModel", "progress: ${it.isInProgress} ")
        it.isInProgress
    }

    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)

    }

    fun refresh(){
        queryLiveData.postValue("R${queryLiveData.value}")
    }
}