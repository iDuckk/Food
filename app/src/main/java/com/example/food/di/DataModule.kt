package com.example.food.di

import android.app.Application
import com.example.food.data.retrofit.ApiFactory
import com.example.food.data.retrofit.FoodService
import com.example.food.data.retrofit.repository.FoodRepositoryImpl
import com.example.food.data.room.foodDB.FoodDao
import com.example.food.data.room.foodDB.FoodDatabase
import com.example.food.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

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

        @ApplicationScope
        @Provides
        fun provideApiService(): FoodService {
            return ApiFactory.getInstance().create(FoodService::class.java)
        }
    }

}