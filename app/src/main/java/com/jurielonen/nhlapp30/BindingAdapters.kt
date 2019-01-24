package com.jurielonen.nhlapp30

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.CalendarContract
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlays
import com.squareup.picasso.Picasso

object BindingAdapters {

    @BindingAdapter("load_image")
    @JvmStatic
    fun setImage(view: ImageView, team: String) {
        view.setImageResource(Helper.chooseImage(team))
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @BindingAdapter("set_constraint")
    @JvmStatic
    fun setConstrains(constraint: ConstraintLayout, play: GamePlays){

        if(play.homeTeam){
            val param = constraint.layoutParams as RecyclerView.LayoutParams
            param.marginStart = 0
            param.marginEnd = 200
            constraint.requestLayout()
        }
        else {
            val param = constraint.layoutParams as RecyclerView.LayoutParams
            param.marginStart = 200
            param.marginEnd = 0
            constraint.requestLayout()
        }
    }

    @BindingAdapter("imageUrl", "error")
    @JvmStatic
    fun loadImage(view: ImageView,  url: String, error: String){
        Picasso.get().load(url).error(Helper.chooseImage(error)).into(view)
    }



}