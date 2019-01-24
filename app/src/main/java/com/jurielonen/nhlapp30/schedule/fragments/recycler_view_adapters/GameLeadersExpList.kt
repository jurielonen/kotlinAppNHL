package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.GameListLeadersHeaderBinding
import com.jurielonen.nhlapp30.databinding.GameListLeadersItemBinding
import com.jurielonen.nhlapp30.schedule.api.PreviewResponseLeaders
import com.jurielonen.nhlapp30.schedule.api.PreviewResponseLeadersPlayers

class GameLeadersExpList (private val leaders: List<PreviewResponseLeaders>): BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): PreviewResponseLeaders {
        return leaders[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val binding: GameListLeadersHeaderBinding
        var view = convertView
        if(view == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context),
                R.layout.game_list_leaders_header, parent, false)
            view = binding.root
        } else {
            binding = view.tag as GameListLeadersHeaderBinding
        }
        binding.name =  (getGroup(groupPosition)).leaderCategory
        view.tag = binding

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return leaders[groupPosition].leaders.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): PreviewResponseLeadersPlayers {
        return leaders[groupPosition].leaders[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return 0
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        val binding: GameListLeadersItemBinding
        var view = convertView
        if(view == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context),
                R.layout.game_list_leaders_item, parent, false)
            view = binding.root
        } else {
            binding = view.tag as GameListLeadersItemBinding
        }
        binding.rank = "${childPosition + 1}"
        binding.url = "http://static.nhle.com/mugs/mlbam/${getChild(groupPosition, childPosition).person.id}@2x.png"
        binding.leader =  getChild(groupPosition, childPosition)
        view.tag = binding

        return view
    }


    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return 0
    }

    override fun getGroupCount(): Int {
        return leaders.size
    }
}