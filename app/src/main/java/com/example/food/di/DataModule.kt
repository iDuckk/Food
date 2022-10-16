package com.example.food.di

import android.app.Application
import com.example.food.data.retrofit.repository.FoodRepositoryImpl
import com.example.food.data.room.foodDB.FoodDao
import com.example.food.data.room.foodDB.FoodDatabase
import com.example.food.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindFoodListRepository(impl : FoodRepositoryImpl): FoodRepository


    companion object{
        @ApplicationScope
        @Provides
        fun providesListDao(application: Application): FoodDao {
            return FoodDatabase.getDatabase(application).foodDao()
        }
    }

}