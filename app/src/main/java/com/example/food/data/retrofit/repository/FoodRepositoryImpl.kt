package com.example.food.data.retrofit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.food.data.retrofit.ApiFactory
import com.example.food.data.retrofit.FoodService
import com.example.food.data.room.foodDB.FoodDao
import com.example.food.data.room.mapper.FoodMapper
import com.example.food.domain.model.FoodItem
import com.example.food.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val foodDao: FoodDao, private val mapper: FoodMapper) :FoodRepository {

    override fun getAllDataList(): LiveData<List<FoodItem>> {
        return Transformations.map(foodDao.getAll()){
            it.map {
                mapper.mapFoodEntityToBillItem(it)
            }
        }
    }

    override suspend fun getItem(name: String): ArrayList<FoodItem> {
        var category = ArrayList<FoodItem>()
        val foodService : FoodService = ApiFactory.getInstance().create(FoodService::class.java)
        foodService.get(name).enqueue(object : retrofit2.Callback<List<FoodItem>?> {
            override fun onResponse(call: Call<List<FoodItem>?>, response: Response<List<FoodItem>?>) {
                //Get data from HTTP
                val responseBody = response.body()!!
                //Clear data if we receive Data
                if(responseBody.isNotEmpty()){
                    CoroutineScope(Dispatchers.IO).launch {
                        foodDao.clearTable()
                    }
                }
                //Save data in RoomDB
                for (Data in responseBody) {
                    CoroutineScope(Dispatchers.IO).launch {
                        foodDao.insert(mapper.mapFoodItemToBillEntity(Data))
                    }
                }
            }

            override fun onFailure(call: Call<List<FoodItem>?>, t: Throwable) {
                Log.d("ERROR", t.message!!)
            }
        })
        return category
    }

}