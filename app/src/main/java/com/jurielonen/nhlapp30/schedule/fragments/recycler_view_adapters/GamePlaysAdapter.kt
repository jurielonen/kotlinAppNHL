package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlays
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GamePlaysHeaderViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GamePlaysViewHolder
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ListPlaysHeader
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ViewType

class GamePlaysAdapter(private val plays: List<ViewType>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->GamePlaysViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_plays, parent, false))
            else->GamePlaysHeaderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_plays_header, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return plays.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)  = getItem(position).let{ plays ->
        when(plays.getViewType()){
            1-> with(holder){
                itemView.tag = plays
                (this as GamePlaysViewHolder).bind(plays as GamePlays)
            }
            else-> with(holder){
                itemView.tag = plays
                (this as GamePlaysHeaderViewHolder).bind((plays as ListPlaysHeader).time.toString())
            }
        }
    }

    private fun getItem(pos: Int): ViewType{
        return plays[pos]
    }
    override fun getItemViewType(position: Int): Int {
        return plays[position].getViewType()
    }

}