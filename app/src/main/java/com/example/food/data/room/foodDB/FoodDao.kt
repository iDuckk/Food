package com.example.food.data.room.foodDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.food.data.room.model.FoodEntity

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_categories")
    fun getAll(): LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : FoodEntity)

    @Query("DELETE FROM food_categories")
    fun clearTable()
}