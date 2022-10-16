package com.example.food.presentation.adapters.foodAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.food.R
import com.example.food.databinding.ItemFoodBinding
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

    @SuppressLint("SetTextI18n", "StringFormatMatches")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)

        holder.name.text = item.name
        holder.dsc.text = item.dsc
        //Check double or Int
        val strMeatFormat: String =
            holder.itemView.context.getResources().
            getString(R.string.price, isDouble(item.price).toString())
        holder.price.text = strMeatFormat
        //Set Picture
        Glide
            .with(holder.itemView.context)
            .load(item.img)
            .error(R.drawable.ic_android_black)
            .into(holder.pic)

    }

    private fun isDouble(num: Double)
    = if(num.toString().substringAfter('.').toInt() == 0) num.toInt() else num


}