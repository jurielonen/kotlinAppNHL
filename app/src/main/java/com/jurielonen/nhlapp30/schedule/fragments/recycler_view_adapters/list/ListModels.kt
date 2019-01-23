package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list

data class ListPlaysHeader(
    val time: Int
): ViewType{
    override fun getViewType() = PlaysConstant.HEADER
}

class ListPlayerHeader(): ViewType{
    override fun getViewType() = SkaterConstants.SKATERHEADER
}

class ListGoaliePlayerHeader(): ViewType{
    override fun getViewType() = SkaterConstants.GOALIEHEADER
}

