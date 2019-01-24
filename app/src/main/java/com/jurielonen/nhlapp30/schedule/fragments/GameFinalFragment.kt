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
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.FragmentGameBinding
import com.jurielonen.nhlapp30.schedule.Injection
import com.jurielonen.nhlapp30.schedule.fragments.pager.GameFinalPagerAdapter
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import kotlinx.android.synthetic.main.fragment_game.*

class GameFinalFragment: Fragment() {

    private var urlRecap = ""
    private var urlExtended = ""
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameFinalViewModel
    private lateinit var mContext: Context
    private var isInProgess = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        Log.d("GameFinalFragment","OnViewCreate")


        mContext = context ?: return binding.root


        val factory = Injection.provideViewModelFactoryGame()
        viewModel = ViewModelProviders.of(this, factory).get(GameFinalViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("GameFinalFragment","OnViewCreated")
        errorText.visibility = View.GONE
        binding.home = arguments!!.getString("HOME_TEAM", "UNKNOWN")
        binding.away = arguments!!.getString("AWAY_TEAM", "UNKNOWN")
        binding.state = arguments!!.getString("STATE", "UNKNOWN")
        binding.time = arguments!!.getString("TIME", "UNKNOWN")
        binding.scoreHome = arguments!!.getString("HOME_RECORD", "UNKNOWN")
        binding.scoreAway = arguments!!.getString("AWAY_RECORD", "UNKNOWN")
        urlRecap = arguments!!.getString("SELECTED_RECAP", "")
        urlExtended = arguments!!.getString("SELECTED_EXTENDED", "")
        binding.urls = true

        viewModel.gameData.observe(this, Observer<GameData> {
            if(it != null && !isInProgess){
                val headers = mContext.resources.getStringArray(R.array.game_final_fragment)
                val mPagerAdapter = GameFinalPagerAdapter(
                    fragmentManager!!,
                    headers,
                    it
                )
                finalViewPager.adapter = mPagerAdapter
            }
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
            errorText.visibility = View.VISIBLE
        })

        viewModel.isRequestInProgress.observe(this, Observer<Boolean> {
            isInProgess = it
            if(it)
                loadingData.visibility = View.VISIBLE
            else
                loadingData.visibility = View.GONE
        })
        viewModel.search(arguments!!.getInt("SELECTED_GAME", 0).toString())
    }
}