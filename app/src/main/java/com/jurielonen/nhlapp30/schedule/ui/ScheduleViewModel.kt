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
        repository.search(it)
    }

    val schedule: LiveData<PagedList<Games>> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("ScheduleViewModel", "list: ${it.data.value} ")
        it.data }

    val networkErrors: LiveData<String> = Transformations.switchMap(scheduleResult
    ) { it ->
        Log.d("ScheduleViewModel", "list: ${it.data.value} ")
        it.networkErrors }

    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)

    }

    fun lastQueryValue(): String? = queryLiveData.value
}