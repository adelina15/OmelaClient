package com.example.omela.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.omela.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.omela.R


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}