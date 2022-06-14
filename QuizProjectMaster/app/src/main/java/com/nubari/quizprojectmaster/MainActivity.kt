package com.nubari.quizprojectmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        /**
         * When creating the NavHostFragment using FragmentContainerView
         * or if manually adding the NavHostFragment to your activity via a FragmentTransaction,
         * attempting to retrieve the NavController in onCreate() of an Activity
         * via Navigation.findNavController(Activity, @IdRes int) will fail.
         * You should retrieve the NavController directly from the NavHostFragment instead.
         * So using the code below would only work when we directly embed the our nav host frag
         * as a fragment in the activity_main.xml file, since we used a fragment container view
         * the following code will fail.
         * **/
        // obtain ref to our nav controller
//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        /**
         * Sets up the ActionBar returned by AppCompatActivity.getSupportActionBar
         * for use with a NavController.
         * By calling this method, the title in the action bar will
         * automatically be updated when the destination changes.
         * **/

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}