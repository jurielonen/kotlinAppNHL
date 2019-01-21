package com.jurielonen.nhlapp30.schedule.fragments

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
import com.jurielonen.nhlapp30.databinding.GameFinalFragmentBinding
import com.jurielonen.nhlapp30.schedule.Injection
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import com.jurielonen.nhlapp30.schedule.ui.GameViewModelFactory

class GameFinalFragment: Fragment() {

    private var gamePK = 0
    private var urlRecap = ""
    private var urlExtended = ""

    private lateinit var viewModel: GameFinalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: GameFinalFragmentBinding = GameFinalFragmentBinding.inflate(inflater, container, false)


        val mContext = context ?: return binding.root


        val factory = Injection.provideViewModelFactoryGame(mContext)
        viewModel = ViewModelProviders.of(this, factory).get(GameFinalViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamePK = arguments!!.getInt("SELECTED_GAME", 0)
        urlRecap = arguments!!.getString("SELECTED_RECAP", "")
        urlExtended = arguments!!.getString("SELECTED_EXTENDED", "")

        viewModel.gameData.observe(this, Observer<PagedList<GameData>> {
            Log.d("FINAL FRAGMENT", "data: $it")
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })


        viewModel.search(gamePK.toString())
    }
}