package com.jurielonen.nhlapp30.schedule

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.data.ScheduleRepository
import com.jurielonen.nhlapp30.schedule.db.ScheduleDb
import com.jurielonen.nhlapp30.schedule.db.ScheduleLocalCache
import com.jurielonen.nhlapp30.schedule.ui.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    /**
     * Creates an instance of [ScheduleLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context?): ScheduleLocalCache {
        val database = ScheduleDb.getInstance(context)
        return ScheduleLocalCache(database.scheduleDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [ScheduleRepository] based on the [ScheduleApi] and a
     * [ScheduleLocalCache]
     */
    private fun provideScheduleRepository(context: Context?): ScheduleRepository {
        return ScheduleRepository(ScheduleApi.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context?): ViewModelProvider.Factory {
        return ViewModelFactory(provideScheduleRepository(context))
    }
}