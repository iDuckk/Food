package com.example.food.presentation.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food.R
import com.example.food.databinding.FragmentMainBinding
import com.example.food.presentation.adapters.categoryAdapter.CategoriesAdapter
import com.example.food.presentation.adapters.categoryAdapter.onClickListenerItem
import com.example.food.domain.model.CategoriesItem

class mainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    val categoriesAdapter: CategoriesAdapter = CategoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()

        initSalesImages()

        initRecViewCategories()

    }

    private fun initRecViewCategories() {
        categoriesAdapter.submitList(createList())

        with(binding.recViewCategories){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

        onClickListenerItem = {
            categoriesAdapter.notifyDataSetChanged()

        }

    }

    private fun createList() :ArrayList<CategoriesItem>{
        var id = 0
        val list :ArrayList<CategoriesItem> = ArrayList<CategoriesItem>()
        val categories = resources.getStringArray(R.array.Categories)

        categories.forEach {
            list.add(CategoriesItem(id++, it))
        }
        return list
    }

    private fun initSalesImages() {
        Glide
            .with(this)
            .load(R.drawable.sale3)
            .override(900,300)
            .transform(CenterCrop(), RoundedCorners(15))
            .into(binding.imSale1)

        Glide
            .with(this)
            .load(R.drawable.sale4)
            .override(900,300)
            .transform(CenterCrop(), RoundedCorners(15))
            .into(binding.imSale2)
    }

    private fun initSpinner(){
        val cities = resources.getStringArray(R.array.Cities)
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, cities)
        binding.spinnerCity.adapter = adapter
    }

    companion object {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}