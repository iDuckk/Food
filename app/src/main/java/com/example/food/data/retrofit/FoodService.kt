package com.example.food.data.retrofit

import com.example.food.domain.model.FoodItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodService {

    @GET("{category}")
    fun get(@Path("category") category: String): Call<List<FoodItem>>

}