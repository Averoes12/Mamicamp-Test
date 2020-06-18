package com.averoes.booksapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.averoes.booksapp.ui.category.DashboardFragment
import com.averoes.booksapp.ui.home.HomeFragment
import com.pandora.bottomnavigator.BottomNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navigator: BottomNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        window.decorView.overlay
        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.fragment_container,
            bottomNavigationView = nav_view,
            rootFragmentsFactory = mapOf(
                R.id.navigation_home to {HomeFragment()},
                R.id.navigation_dashboard to {DashboardFragment()}
            ),
            defaultTab = R.id.navigation_home,
            activity = this
        )
    }
}