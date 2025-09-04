package com.example.movies

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination,_ ->
            when(destination.id){
                R.id.homeFragment ->  binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.exploreFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.myListFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.profileFragment -> binding.bottomNavigationView.visibility = View.VISIBLE

                else ->   binding.bottomNavigationView.visibility = View.GONE

            }

        }

        binding.bottomNavigationView.setupWithNavController(navController)

    }
}