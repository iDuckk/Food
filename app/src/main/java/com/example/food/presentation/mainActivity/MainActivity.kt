package com.example.food.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.food.R
import com.example.food.data.retrofit.repository.FoodRepositoryImpl
import com.example.food.domain.useCases.GetCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavBottom()

    }

    //Set Bottom navigation
    private fun initNavBottom(){
        val navBot = findViewById<BottomNavigationView>(R.id.bottomNavigate)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navBot.setupWithNavController(navHostFragment.navController)
    }
}