package com.jurielonen.nhlapp30.schedule.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.GameFinalFragmentBinding
import com.jurielonen.nhlapp30.schedule.Injection
import com.jurielonen.nhlapp30.schedule.fragments.game_final.GameFinalPagerAdapter
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import com.jurielonen.nhlapp30.schedule.ui.GameViewModelFactory
import kotlinx.android.synthetic.main.game_final_fragment.*

class GameFinalFragment: Fragment() {

    private var urlRecap = ""
    private var urlExtended = ""
    private lateinit var binding: GameFinalFragmentBinding
    private lateinit var viewModel: GameFinalViewModel
    private lateinit var mContext: Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = GameFinalFragmentBinding.inflate(inflater, container, false)


        mContext = context ?: return binding.root


        val factory = Injection.provideViewModelFactoryGame(mContext)
        viewModel = ViewModelProviders.of(this, factory).get(GameFinalViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.home = arguments!!.getString("HOME_TEAM", "UNKNOWN")
        binding.away = arguments!!.getString("AWAY_TEAM", "UNKNOWN")
        binding.state = arguments!!.getString("STATE", "UNKNOWN")
        binding.time = arguments!!.getString("TIME", "UNKNOWN")
        binding.scoreHome = arguments!!.getString("HOME_RECORD", "UNKNOWN")
        binding.scoreAway = arguments!!.getString("AWAY_RECORD", "UNKNOWN")
        urlRecap = arguments!!.getString("SELECTED_RECAP", "")
        urlExtended = arguments!!.getString("SELECTED_EXTENDED", "")

        viewModel.gameData.observe(this, Observer<PagedList<GameData>> {
            Log.d("FINAL FRAGMENT", "data: ${it.size}")
            if(it.size > 0){

                val headers = mContext.resources.getStringArray(R.array.game_final_fragment)
                val mPagerAdapter = GameFinalPagerAdapter(fragmentManager!!, headers.size, headers, it[0]!!)
                finalViewPager.adapter = mPagerAdapter

            }

        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })


        viewModel.search(arguments!!.getInt("SELECTED_GAME", 0).toString())
    }
}