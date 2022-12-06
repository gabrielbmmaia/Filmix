package com.example.filmix.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.filmix.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //    lateinit var navController: NavController
//    lateinit var appBarConfiguration: AppBarConfiguration
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
////        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
//        navController = navHostFragment.navController
//
//        bottomNavigation.setupWithNavController(navController)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
//        val navController = navHostFragment.navController
//        val appBarConfig = AppBarConfiguration(navController.graph)

//        binding.toolbar.setupWithNavController(navController,appBarConfig)


    }

//    override fun onSupportNavigateUp(): Boolean {
////        return NavigationUI.navigateUp(navController, appBarConfiguration)
//    }
}