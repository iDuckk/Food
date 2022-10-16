package com.example.food.presentation.adapters.foodAdapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food.R
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.databinding.ItemFoodBinding
import com.example.food.domain.model.CategoriesItem
import com.example.food.domain.model.FoodItem
import javax.inject.Inject

class FoodAdapter @Inject constructor() :
    ListAdapter<FoodItem, FoodViewHolder>(FoodCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)

        holder.name.text = item.name
        holder.dsc.text = item.dsc

        holder.price.text = "от " + item.price.toInt() + " р"


        Glide
            .with(holder.itemView.context)
            .load(item.img)
            .into(holder.pic)

    }
}