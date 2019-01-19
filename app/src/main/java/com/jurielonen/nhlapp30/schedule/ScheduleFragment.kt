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
import com.jurielonen.nhlapp30.databinding.ScheduleFragmentBinding
import com.jurielonen.nhlapp30.schedule.model.Games
import com.jurielonen.nhlapp30.schedule.ui.GameAdapter
import com.jurielonen.nhlapp30.schedule.ui.ScheduleViewModel
import kotlinx.android.synthetic.main.schedule_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment: Fragment() {

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var adapter: GameAdapter

    private val dfAPI = SimpleDateFormat("yyyy-MM-dd")
    private val dfShow = SimpleDateFormat("dd.MM.yyyy")
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ScheduleFragmentBinding.inflate(inflater, container, false)
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
        viewModel.schedule.observe(this, Observer<PagedList<Games>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
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
        val correctedMonth = month + 1
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
                    scheduleRecyclerView.visibility = View.GONE
                }
                View.VISIBLE -> {
                    calendarView.visibility = View.GONE
                    scheduleRecyclerView.visibility = View.VISIBLE
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

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
    }

}