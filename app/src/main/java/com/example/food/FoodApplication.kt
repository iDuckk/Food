package com.example.food

import android.app.Application
import com.example.food.di.DaggerApplicationComponent

class FoodApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

}