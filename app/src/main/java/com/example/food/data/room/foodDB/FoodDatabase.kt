package com.example.food.data.room.foodDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.food.data.room.model.FoodEntity

@Database(entities = [FoodEntity::class], version = 2, exportSchema = false)
abstract  class FoodDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "categories_database"
                )
                    .build() //.fallbackToDestructiveMigration()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}