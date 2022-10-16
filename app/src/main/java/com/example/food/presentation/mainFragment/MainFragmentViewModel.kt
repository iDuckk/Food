package com.example.food.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.food.domain.model.FoodItem
import com.example.food.domain.useCases.GetCategory
import com.example.food.domain.useCases.GetLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCategory: GetCategory,
    private val getList: GetLiveData
): ViewModel() {

    fun getCategory(category: String){
        CoroutineScope(IO).launch {
            getCategory.invoke(category)
        }
    }

    fun getLiveDate() = getList.invoke()


}