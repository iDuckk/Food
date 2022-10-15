package com.example.food.domain.useCases

import com.example.food.domain.repository.FoodRepository

class GetCategory(private val repo : FoodRepository) {

    suspend operator fun invoke(name : String) = repo.getItem(name)

}