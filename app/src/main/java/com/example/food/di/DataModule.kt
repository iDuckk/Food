package com.example.food.di

import com.example.food.data.retrofit.repository.FoodRepositoryImpl
import com.example.food.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindFoodListRepository(impl : FoodRepositoryImpl): FoodRepository


//    companion object{
//        @ApplicationScope
//        @Provides
//        fun providesListDao(application: Application): BillDao {
//            return BillDatabase.getDatabase(application).billDao()
//        }
//    }

}