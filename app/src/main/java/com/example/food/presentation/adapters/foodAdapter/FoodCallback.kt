package com.example.food.presentation.adapters.foodAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.food.domain.model.CategoriesItem
import com.example.food.domain.model.FoodItem

class FoodCallback: DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.id_db == newItem.id_db
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}