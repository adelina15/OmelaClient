package com.example.omela.view.login

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.omela.R

class RegistrationActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        //Make status bar white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window: Window = window
            val decorView: View = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = Color.WHITE
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.registration_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}