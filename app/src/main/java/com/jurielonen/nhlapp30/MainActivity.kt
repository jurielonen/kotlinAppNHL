package com.jurielonen.nhlapp30

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        bottom_nav_view?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val dest = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException){
                Integer.toString(destination.id)
            }
            Toast.makeText(this@MainActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }
}
