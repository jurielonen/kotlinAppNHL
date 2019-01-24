package com.jurielonen.nhlapp30


import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
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

    @BindingAdapter("app:goneUnless")
    @JvmStatic
    fun goneUnless(button: Button, visible: Boolean) {
        button.visibility = if (visible) View.VISIBLE else View.GONE
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

    @BindingAdapter(value = ["app:progressScaled"])
    @JvmStatic
    fun setProgress(progressBar: ProgressBar, progress: Int) {
        progressBar.progress = progress
    }

}