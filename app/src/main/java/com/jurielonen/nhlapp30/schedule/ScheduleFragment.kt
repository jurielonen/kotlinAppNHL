package com.jurielonen.nhlapp30.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.FragmentScheduleBinding
import com.jurielonen.nhlapp30.schedule.model.Games
import com.jurielonen.nhlapp30.schedule.model.LoadingEnum
import com.jurielonen.nhlapp30.schedule.ui.GameAdapter
import com.jurielonen.nhlapp30.schedule.ui.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment: Fragment() {

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var adapter: GameAdapter

    private val dfAPI = SimpleDateFormat("yyyy-MM-dd")
    private val dfShow = SimpleDateFormat("dd.MM.yyyy")
    private val calendar = Calendar.getInstance()
    private var isInProgress = LoadingEnum.LOADING
    private var showList = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)
        binding.minusListener = minusDate
        binding.plusListener = plusDate
        binding.calendarListener = dateClicked


        val mContext = context ?: return binding.root
        adapter = GameAdapter()
        binding.scheduleRecyclerView.adapter = adapter

        val factory = Injection.provideViewModelFactory(mContext)
        viewModel = ViewModelProviders.of(this, factory).get(ScheduleViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyList.visibility = View.GONE
        calendarView.visibility = View.GONE
        initAdapter()
        val query = getCurrentDateAPI()
        viewModel.searchRepo(query)
        initSearch()
    }

    private fun initAdapter() {
        viewModel.schedule.observe(this, Observer<List<Games>> {
            Log.d("Activity", "list: ${it?.size}")
            if(it.isNotEmpty())
                adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
            emptyList.text = resources.getString(R.string.error_view)
        })

        scheduleSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.isRequestInProgress.observe(this, Observer<LoadingEnum> {
            isInProgress = it
            scheduleSwipeRefresh.isRefreshing = it == LoadingEnum.REFRESH
            when (it) {
                LoadingEnum.LOADING -> {
                    scheduleRecyclerView.visibility = View.GONE
                    emptyList.visibility = View.GONE
                    scheduleProgressBar.visibility = View.VISIBLE
                }
                LoadingEnum.SHOW_DATA, LoadingEnum.LOADED, LoadingEnum.ERROR -> {
                    scheduleProgressBar.visibility = View.GONE
                    showList = false
                    showEmptyList(showList)
                }
                LoadingEnum.SHOW_EMPTY ->{
                    scheduleProgressBar.visibility = View.GONE
                    showList = true
                    showEmptyList(showList)
                }
            }
        })
    }

    private fun initSearch() {
        calendarButton.text = getCurrentDateShow()
        calendarView.setOnDateChangeListener(dateChanged)
    }

    private fun updateRepoListFromInput() {
        getCurrentDateAPI().let {
            if (it.isNotEmpty()) {
                scheduleRecyclerView.scrollToPosition(0)
                viewModel.searchRepo(it)
                adapter.submitList(null)
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            scheduleRecyclerView.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            scheduleRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun getCurrentDateAPI(): String {
        return dfAPI.format(calendar.time)
    }

    private fun getCurrentDateShow(): String {
        return dfShow.format(calendar.time)
    }

    private val dateChanged = CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
        calendar.set(year, month, dayOfMonth)
        calendarButton.text = getCurrentDateShow()
        calendarView.visibility = View.GONE
        emptyList.visibility = View.GONE
        scheduleRecyclerView.visibility = View.VISIBLE
        updateRepoListFromInput()
    }

    private val dateClicked = object: View.OnClickListener {
        override fun onClick(v: View?) {
            when(calendarView.visibility) {
                View.GONE ->{
                    calendarView.visibility = View.VISIBLE
                    emptyList.visibility = View.GONE
                    scheduleProgressBar.visibility = View.GONE
                    scheduleRecyclerView.visibility = View.GONE
                }
                View.VISIBLE -> {
                    calendarView.visibility = View.GONE
                    showEmptyList(showList)
                }
            }
        }
    }

    private val plusDate = object: View.OnClickListener {
        override fun onClick(v: View?) {
            calendar.add(Calendar.DATE, 1)
            calendarView.date = calendar.timeInMillis
            calendarButton.text = getCurrentDateShow()
            updateRepoListFromInput()
        }
    }
    private val minusDate = object: View.OnClickListener {
        override fun onClick(v: View?) {
            calendar.add(Calendar.DATE, -1)
            calendarView.date = calendar.timeInMillis
            calendarButton.text = getCurrentDateShow()
            updateRepoListFromInput()
        }
    }
}