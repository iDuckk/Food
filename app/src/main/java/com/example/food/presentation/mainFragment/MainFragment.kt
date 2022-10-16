package com.example.food.presentation.mainFragment

import android.content.Context.MODE_PRIVATE
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
import com.example.food.FoodApplication
import com.example.food.R
import com.example.food.databinding.FragmentMainBinding
import com.example.food.presentation.adapters.categoryAdapter.CategoriesAdapter
import com.example.food.presentation.adapters.categoryAdapter.onClickListenerItem
import com.example.food.domain.model.CategoriesItem
import com.example.food.presentation.adapters.categoryAdapter.selectedItem
import com.example.food.presentation.adapters.foodAdapter.FoodAdapter
import com.example.food.utils.isOnline
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!


    @Inject
    lateinit var viewModel: MainFragmentViewModel
    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter
    @Inject
    lateinit var foodAdapter: FoodAdapter
    @Inject
    lateinit var isOnline: isOnline

    var listCategory :ArrayList<CategoriesItem> = ArrayList<CategoriesItem>()

    private val component by lazy {
        (requireActivity().application as FoodApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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

        listCategory = createList()

        initSpinner()

        initSalesImages()

        initRecViewCategories()

        initRecViewFood()

        setList()

    }

    companion object {
        private const val CATEGORY_TYPE = "categoryType"
        private const val DEFAULT_CATEGORY_TYPE = 0
        private const val firstCategory = "bbqs"

    }

    private fun setList(){
        //If online set first category
        if(isOnline()) {
            viewModel.getCategory(firstCategory)
            setSharePref(DEFAULT_CATEGORY_TYPE)
        }
        viewModel.getLiveDate().observe(viewLifecycleOwner){
            foodAdapter.submitList(it.toMutableList())
        }
    }

    private fun initRecViewFood() {
        with(binding.recFood){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = foodAdapter
        }
    }

    private fun initRecViewCategories() {
        //Set category View
        selectedItem = setCategory()
        //Set List
        categoriesAdapter.submitList(listCategory)
        //Set recView Horizontal
        with(binding.recViewCategories){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }
        //Scroll to selected Position
        binding.recViewCategories.layoutManager?.scrollToPosition(selectedItem!!)
        //ClickListener
        onClickListenerItem = {
            viewModel.getCategory(it.category)
            //Save category View
            if(selectedItem != null)
                setSharePref(it.id)
        }

    }

    private fun createList() :ArrayList<CategoriesItem>{
        var id = 0
        var list :ArrayList<CategoriesItem> = ArrayList<CategoriesItem>()
        //Get from resource String
        val categories = resources.getStringArray(R.array.Categories)

        categories.forEach {
            list.add(CategoriesItem(id, it))
            id++
        }
        return list
    }

    private fun initSalesImages() {
        //First imageView
        Glide
            .with(this)
            .load(R.drawable.sale3)
            .override(900,300)
            .transform(CenterCrop(), RoundedCorners(15))
            .into(binding.imSale1)
        //Second imageView
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

    private fun setCategory(): Int {
        //Get statement of Currency in Share preference
        val sharedPref = requireActivity().getPreferences(MODE_PRIVATE)
        val categoryPos = sharedPref.getInt(CATEGORY_TYPE, DEFAULT_CATEGORY_TYPE)
        return categoryPos
    }

    private fun setSharePref(category: Int) {
        //Save statement of Currency in Share preference
        val sharedPref = requireActivity().getPreferences(MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(CATEGORY_TYPE, category)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}