package com.example.food.presentation.adapters.foodAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.databinding.ItemFoodBinding

class FoodViewHolder (binding: ItemFoodBinding)
    : RecyclerView.ViewHolder(binding.root) {

    var pic = binding.imFood
    var name = binding.tvName
    var dsc = binding.tvDsc
    var price = binding.tvPrice
}