package com.jurielonen.nhlapp30.schedule

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.data.GamePreviewGetter
import com.jurielonen.nhlapp30.schedule.data.GameRepository
import com.jurielonen.nhlapp30.schedule.data.ScheduleRepository
import com.jurielonen.nhlapp30.schedule.db.ScheduleDb
import com.jurielonen.nhlapp30.schedule.db.ScheduleLocalCache
import com.jurielonen.nhlapp30.schedule.fragments.GamePreviewViewModelFactory
import com.jurielonen.nhlapp30.schedule.fragments.GameViewModelFactory
import com.jurielonen.nhlapp30.schedule.ui.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    private fun provideCache(context: Context?): ScheduleLocalCache {
        val database = ScheduleDb.getInstance(context)
        return ScheduleLocalCache(database.scheduleDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideScheduleRepository(context: Context?): ScheduleRepository {
        return ScheduleRepository(ScheduleApi.create(), provideCache(context), Executors.newSingleThreadExecutor())
    }

    fun provideViewModelFactory(context: Context?): ViewModelProvider.Factory {
        return ViewModelFactory(provideScheduleRepository(context))
    }

    private fun provideGameRepository(): GameRepository {
        return GameRepository(ScheduleApi.create())
    }

    fun provideViewModelFactoryGame(): ViewModelProvider.Factory {
        return GameViewModelFactory(provideGameRepository())
    }

    private fun provideGamePreviewRepository(): GamePreviewGetter {
        return GamePreviewGetter(ScheduleApi.create())
    }

    fun provideViewModelFactoryGamePreview(): ViewModelProvider.Factory {
        return GamePreviewViewModelFactory(provideGamePreviewRepository())
    }
}