package com.example.food.domain.repository

import androidx.lifecycle.LiveData
import com.example.food.domain.model.FoodItem

interface FoodRepository {

    suspend fun getItem(name : String) : ArrayList<FoodItem>

    fun getAllDataList() : LiveData<List<FoodItem>>
}