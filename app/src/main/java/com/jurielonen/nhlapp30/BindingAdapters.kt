package com.jurielonen.nhlapp30

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("app:setImage")
    @JvmStatic
    fun setImage(view: ImageView, team: String) {
        Helper.chooseImage(team, view)
    }
}