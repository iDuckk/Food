package com.example.food.presentation.adapters.categoryAdapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import com.example.food.R
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.domain.model.CategoriesItem
import javax.inject.Inject

var onClickListenerItem: ((item: CategoriesItem) -> Unit)? = null
var selectedItem: Int? = null
var prevItem: TextView? = null

class CategoriesAdapter @Inject constructor() :
    ListAdapter<CategoriesItem, CategoriesViewHolder>(CategoriesCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        //Set color text
        holder.button.setTextColor(holder.itemView.context.getColorStateList(R.drawable.text_color_button))
        //If TextView Enable/Disable
        if (selectedItem == position) { //Disable
            holder.button.setBackgroundResource(R.drawable.border_button_category_clicked)
            holder.button.isEnabled = false
        } else {                        //Enable
            holder.button.setBackgroundResource(R.drawable.border_button_category)
            holder.button.isEnabled = true
        }

        holder.button.text = item.category

        holder.button.setOnClickListener {
            onClickListenerItem?.invoke(item)
            //If items was selected
            if (selectedItem != null) {
                prevItem?.setBackgroundResource(R.drawable.border_button_category)
                prevItem?.isEnabled = true
                notifyDataSetChanged()
            }
            //Set color state TextView
            it.isEnabled = false
            it.setBackgroundResource(R.drawable.border_button_category_clicked)
            selectedItem = item.id
        }
    }
}