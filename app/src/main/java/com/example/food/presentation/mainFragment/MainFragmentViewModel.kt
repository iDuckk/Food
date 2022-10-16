package com.example.food.presentation.mainFragment

import androidx.lifecycle.ViewModel
import com.example.food.domain.useCases.GetCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCategory: GetCategory
): ViewModel() {

    fun getCategory(category: String){
        CoroutineScope(IO).launch {
            getCategory.invoke(category)
        }
    }

}