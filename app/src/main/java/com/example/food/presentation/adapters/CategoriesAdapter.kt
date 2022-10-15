package com.example.food.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import com.example.food.R
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.presentation.model.CategoriesItem

var onClickListenerItem: ((item : CategoriesItem) -> Unit)? = null
var selectedItem: Int? = null
var prevItem: TextView? = null

class CategoriesAdapter(): ListAdapter<CategoriesItem, CategoriesViewHolder>(CategoriesCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)

        holder.button.setTextColor(holder.itemView.context.getColorStateList(R.drawable.text_color_button))

            if(selectedItem == position){
                holder.button.setBackgroundResource(R.drawable.border_button_category_clicked)
                holder.button.isEnabled = false
            }else{
                holder.button.setBackgroundResource(R.drawable.border_button_category)
                holder.button.isEnabled = true
            }

        holder.button.text = item.category

        holder.button.setOnClickListener {
            onClickListenerItem?.invoke(item)
            if(selectedItem != null){
                prevItem?.setBackgroundResource(R.drawable.border_button_category)
                prevItem?.isEnabled = true
            }
            it.isEnabled = false
            it.setBackgroundResource(R.drawable.border_button_category_clicked)
            selectedItem = position
        }
    }
}