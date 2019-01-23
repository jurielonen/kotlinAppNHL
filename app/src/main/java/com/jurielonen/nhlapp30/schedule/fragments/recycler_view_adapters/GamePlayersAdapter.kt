package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.fragments.model.GameGoalie
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlayer
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GameGoalieHeaderViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GameGoalieViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GamePlayersHeaderViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GamePlayersViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ViewType

class GamePlayersAdapter(private val skaters: List<ViewType>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->GamePlayersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_skater_stats, parent, false))
            2->GameGoalieViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_goalie_stats, parent, false))
            3->GamePlayersHeaderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_skater_header, parent, false))
            else->GameGoalieHeaderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_goalie_header, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return skaters.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)  = getItem(position).let{ game ->
        when(game.getViewType()){
            1-> with(holder){
                        itemView.tag = game
                        (this as GamePlayersViewHolder).bind(game as GamePlayer)
            }
            2-> with(holder){
                        itemView.tag = game
                        (this as GameGoalieViewHolder).bind(game as GameGoalie)
            }
            3-> with(holder) {
                        itemView.tag = game
                        (this as GamePlayersHeaderViewHolder).bind()
            }
            else-> with(holder){
                        itemView.tag = game
                        (this as GameGoalieHeaderViewHolder).bind()
            }
        }
    }

    private fun getItem(pos: Int): ViewType{
        return skaters[pos]
    }
    override fun getItemViewType(position: Int): Int {
        return skaters[position].getViewType()
    }
}