package com.example.food.data.retrofit

import com.example.food.presentation.model.Category
import com.example.food.presentation.model.FoodItem
import retrofit2.Call
import retrofit2.http.GET

interface FoodService {

    @GET("pizzas")
    fun get(): Call<List<FoodItem>>

}