package com.example.food.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.food.R
import com.example.food.data.retrofit.ApiFactory
import com.example.food.data.retrofit.FoodService
import com.example.food.databinding.ActivityMainBinding
import com.example.food.presentation.model.Category
import com.example.food.presentation.model.FoodItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initNavBottom()

        val foodService : FoodService = ApiFactory.getInstance().create(FoodService::class.java)
        foodService.get().enqueue(object : retrofit2.Callback<List<FoodItem>?> {
            override fun onResponse(call: Call<List<FoodItem>?>, response: Response<List<FoodItem>?>) {
                val responseBody = response.body()!!
                var category = ArrayList<String>()

                //Create List of genres
                for (Data in responseBody) {     //In each
                    Log.d("TAG", "<work" +Data.name)
                }
            }

            override fun onFailure(call: Call<List<FoodItem>?>, t: Throwable) {
                Log.d("TAG", "ERROR ****" + t.message!!)
            }
        })
    }


    private fun initNavBottom(){
        val navBot = findViewById<BottomNavigationView>(R.id.bottomNavigate)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navBot.setupWithNavController(navHostFragment.navController)
    }
}