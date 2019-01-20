package com.jurielonen.nhlapp30

import android.view.View
import android.widget.ImageView
import com.jurielonen.nhlapp30.schedule.model.Games

object Helper {

    fun chooseImage(team: String, image: ImageView) {
        when (team) {
            "Anaheim Ducks" -> image.setImageResource(R.drawable.logo_ana)

            "Arizona Coyotes" -> image.setImageResource(R.drawable.logo_ari)

            "Boston Bruins" -> image.setImageResource(R.drawable.logo_bos)

            "Buffalo Sabres" -> image.setImageResource(R.drawable.logo_buf)

            "Carolina Hurricanes" -> image.setImageResource(R.drawable.logo_car)

            "Columbus Blue Jackets" -> image.setImageResource(R.drawable.logo_cbj)

            "Calgary Flames" -> image.setImageResource(R.drawable.logo_cgy)

            "Chicago Blackhawks" -> image.setImageResource(R.drawable.logo_chi)

            "Colorado Avalanche" -> image.setImageResource(R.drawable.logo_col)

            "Dallas Stars" -> image.setImageResource(R.drawable.logo_dal)

            "Detroit Red Wings" -> image.setImageResource(R.drawable.logo_det)

            "Edmonton Oilers" -> image.setImageResource(R.drawable.logo_edm)

            "Florida Panthers" -> image.setImageResource(R.drawable.logo_fla)

            "Los Angeles Kings" -> image.setImageResource(R.drawable.logo_la)

            "Minnesota Wild" -> image.setImageResource(R.drawable.logo_min)

            "Montréal Canadiens" -> image.setImageResource(R.drawable.logo_mtl)

            "Nashville Predators" -> image.setImageResource(R.drawable.logo_nas)

            "New Jersey Devils" -> image.setImageResource(R.drawable.logo_nj)

            "New York Islanders" -> image.setImageResource(R.drawable.logo_nyi)

            "New York Rangers" -> image.setImageResource(R.drawable.logo_nyr)

            "Ottawa Senators" -> image.setImageResource(R.drawable.logo_ott)

            "Philadelphia Flyers" -> image.setImageResource(R.drawable.logo_phi)

            "Pittsburgh Penguins" -> image.setImageResource(R.drawable.logo_pit)

            "San Jose Sharks" -> image.setImageResource(R.drawable.logo_sj)

            "St. Louis Blues" -> image.setImageResource(R.drawable.logo_stl)

            "Tampa Bay Lightning" -> image.setImageResource(R.drawable.logo_tb)

            "Toronto Maple Leafs" -> image.setImageResource(R.drawable.logo_tor)

            "Vancouver Canucks" -> image.setImageResource(R.drawable.logo_van)

            "Vegas Golden Knights" -> image.setImageResource(R.drawable.logo_vgk)

            "Washington Capitals" -> image.setImageResource(R.drawable.logo_was)

            "Winnipeg Jets" -> image.setImageResource(R.drawable.logo_wpg)

            else -> image.setImageResource(R.drawable.right_arrow)
        }
    }
}