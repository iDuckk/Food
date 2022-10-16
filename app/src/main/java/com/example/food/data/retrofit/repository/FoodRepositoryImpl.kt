package com.example.food.data.retrofit.repository

import android.util.Log
import com.example.food.data.retrofit.ApiFactory
import com.example.food.data.retrofit.FoodService
import com.example.food.domain.model.FoodItem
import com.example.food.domain.repository.FoodRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor() :FoodRepository {

    override suspend fun getItem(name: String): ArrayList<FoodItem> {
        var category = ArrayList<FoodItem>()
        val foodService : FoodService = ApiFactory.getInstance().create(FoodService::class.java)
        foodService.get(name).enqueue(object : retrofit2.Callback<List<FoodItem>?> {
            override fun onResponse(call: Call<List<FoodItem>?>, response: Response<List<FoodItem>?>) {
                val responseBody = response.body()!!

                for (Data in responseBody) {     //In each
                    category.add(Data)
                    Log.d("TAG", Data.name)
                }
            }

            override fun onFailure(call: Call<List<FoodItem>?>, t: Throwable) {
                Log.d("TAG", "ERROR ****" + t.message!!)
            }
        })
        return category
    }
}