package com.example.food.presentation.adapters.categoryAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.food.domain.model.CategoriesItem

class CategoriesCallback: DiffUtil.ItemCallback<CategoriesItem>() {
    override fun areItemsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean {
        return oldItem == newItem
    }
}