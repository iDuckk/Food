package com.example.food.data.room.mapper

import com.example.food.data.room.model.FoodEntity
import com.example.food.domain.model.FoodItem
import javax.inject.Inject

class FoodMapper @Inject constructor(){
    fun mapFoodItemToBillEntity(item : FoodItem) = FoodEntity( //val id_db: Int,
        id_db = item.id_db,
        id = item.id,
        img = item.img,
        name = item.name,
        dsc = item.dsc,
        price = item.price,
        rate = item.rate,
        country = item.country
    )


    fun mapFoodEntityToBillItem(item : FoodEntity) = FoodItem(
        id_db = item.id_db,
        id = item.id,
        img = item.img,
        name = item.name,
        dsc = item.dsc,
        price = item.price,
        rate = item.rate,
        country = item.country
    )
}