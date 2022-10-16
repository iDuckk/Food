package com.example.food.domain.useCases

import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject

class GetCategory @Inject constructor(private val repo : FoodRepository) {

    suspend operator fun invoke(name : String) = repo.getItem(name)

}