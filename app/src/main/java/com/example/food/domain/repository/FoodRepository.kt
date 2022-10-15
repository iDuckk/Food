package com.example.food.domain.repository

import com.example.food.domain.model.FoodItem

interface FoodRepository {

    suspend fun getItem(name : String) : ArrayList<FoodItem>
}