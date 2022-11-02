package com.example.food.domain.repository

import androidx.lifecycle.LiveData
import com.example.food.domain.model.FoodItem
import retrofit2.Call

interface FoodRepository {

    suspend fun getItems(name : String) : Call<List<FoodItem>>

    fun getAllDataList() : LiveData<List<FoodItem>>
}