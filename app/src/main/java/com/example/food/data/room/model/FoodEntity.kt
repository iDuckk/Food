package com.example.food.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_categories")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id_db: Int,
    val id: String,
    val img: String,
    val name: String,
    val dsc: String,
    val price: Double,
    val rate: Int,
    val country: String)
