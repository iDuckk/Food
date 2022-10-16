package com.example.food.domain.useCases

import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject

class GetLiveData @Inject constructor(private val repo : FoodRepository)  {

    operator fun invoke() = repo.getAllDataList()

}