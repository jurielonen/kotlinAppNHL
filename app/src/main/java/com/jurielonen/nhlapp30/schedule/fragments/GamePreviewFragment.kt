package com.jurielonen.nhlapp30.schedule.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.GameFinalFragmentBinding
import com.jurielonen.nhlapp30.schedule.Injection
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewData
import com.jurielonen.nhlapp30.schedule.fragments.pager.GamePreviewPagerAdapter
import kotlinx.android.synthetic.main.game_final_fragment.*

class GamePreviewFragment: Fragment(){

private lateinit var binding: GameFinalFragmentBinding
private lateinit var viewModel: GamePreviewViewModel
private lateinit var mContext: Context
private var isInProgess = true


override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = GameFinalFragmentBinding.inflate(inflater, container, false)


    mContext = context ?: return binding.root


    val factory = Injection.provideViewModelFactoryGamePreview()
    viewModel = ViewModelProviders.of(this, factory).get(GamePreviewViewModel::class.java)

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

    viewModel.gameData.observe(this, Observer<GamePreviewData> {
        if(it != null && !isInProgess){
            val headers = mContext.resources.getStringArray(R.array.game_final_fragment)
            val mPagerAdapter = GamePreviewPagerAdapter(fragmentManager!!, headers, it)
            finalViewPager.adapter = mPagerAdapter
        }
    })

    viewModel.networkErrors.observe(this, Observer<String> {
        Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
    })

    viewModel.isRequestInProgress.observe(this, Observer<Boolean> {
        isInProgess = it
        if(it)
            loadingData.visibility = View.VISIBLE
        else
            loadingData.visibility = View.GONE
    })
    viewModel.search("${arguments!!.getInt("HOME_ID", 0)},${arguments!!.getInt("AWAY_ID", 0)}")
}
}