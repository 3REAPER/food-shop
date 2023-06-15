package ru.pervukhin.food_shop.ui.category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.Category
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.ui.product.ProductFragment

class CategoryFragment : Fragment(), OnDishClickListener {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var scrollView: HorizontalScrollView
    private var selectedTag = Dish.TAG_AlL_DISHES

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        val title = view.findViewById<TextView>(R.id.title)
        scrollView = view.findViewById(R.id.scroll_view)
        val tagAllDishes = view.findViewById<TextView>(R.id.all_dishes)
        val tagSalad = view.findViewById<TextView>(R.id.salad)
        val tagWithRice = view.findViewById<TextView>(R.id.with_rice)
        val tagWithFish = view.findViewById<TextView>(R.id.with_fish)
        val tagRolls = view.findViewById<TextView>(R.id.rolls)
        val loading = view.findViewById<FrameLayout>(R.id.loading)
        val condition = view.findViewById<TextView>(R.id.condition)
        val back = view.findViewById<ImageView>(R.id.back)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = DishesAdapter(this)
        recyclerView.adapter = adapter

        viewModel.getAll()

        viewModel.repositoryLiveData.observe(viewLifecycleOwner){
            when(it){
                CategoryViewModel.DishesState.Loading -> loading.visibility = View.VISIBLE
                is CategoryViewModel.DishesState.Success -> {
                    if (selectedTag == it.tag) {
                        adapter.setList(it.dishes)
                        loading.visibility = View.GONE
                        condition.visibility = View.GONE
                    }
                }
                CategoryViewModel.DishesState.Empty ->{
                    condition.text = resources.getString(R.string.empty_dishes)
                    condition.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
                CategoryViewModel.DishesState.NoInternetConnection -> {
                    condition.text = resources.getString(R.string.no_internet)
                    condition.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }

        arguments.let {
            if (it != null){
                when(it.get(Category.TAG)){
                    Category.BREAD -> title.text = resources.getString(R.string.bread)
                    Category.FAST_FOOD -> title.text = resources.getString(R.string.fast_food)
                    Category.ASIAN_KITCHEN -> title.text = resources.getString(R.string.asian_kitchen)
                    Category.SOUPS -> title.text = resources.getString(R.string.soups)
                }
            }
        }

        tagAllDishes.setOnClickListener {
            if (selectedTag != Dish.TAG_AlL_DISHES) {
                selectedTag = Dish.TAG_AlL_DISHES
                tagSelected(tagAllDishes, tagSalad, tagWithRice, tagWithFish, tagRolls)
                viewModel.getByTag(Dish.TAG_AlL_DISHES)
            }
        }


        tagSalad.setOnClickListener {
            if (selectedTag != Dish.TAG_SALAD) {
                selectedTag = Dish.TAG_SALAD
                tagSelected(tagSalad, tagAllDishes, tagWithRice, tagWithFish, tagRolls)
                viewModel.getByTag(Dish.TAG_SALAD)
            }
        }


        tagWithRice.setOnClickListener {
            if (selectedTag != Dish.TAG_WITH_RICE) {
                selectedTag = Dish.TAG_WITH_RICE
                tagSelected(tagWithRice, tagAllDishes, tagSalad, tagWithFish, tagRolls)
                viewModel.getByTag(Dish.TAG_WITH_RICE)
            }
        }


        tagWithFish.setOnClickListener {
            if (selectedTag != Dish.TAG_WITH_FISH) {
                selectedTag = Dish.TAG_WITH_FISH
                tagSelected(tagWithFish, tagAllDishes, tagSalad, tagWithRice, tagRolls)
                viewModel.getByTag(Dish.TAG_WITH_FISH)
            }
        }


        tagRolls.setOnClickListener {
            if (selectedTag != Dish.TAG_WITH_ROLLS) {
                selectedTag = Dish.TAG_WITH_ROLLS
                tagSelected(tagRolls, tagAllDishes, tagSalad, tagWithRice, tagWithFish)
                viewModel.getByTag(Dish.TAG_WITH_ROLLS)
            }
        }

        back.setOnClickListener {
            (activity as MainActivity).navigateToHome()
        }

        return view
    }

    private fun tagSelected(selectedTag: TextView, vararg tags: TextView) {
        scrollView.smoothScrollTo(selectedTag.x.toInt(),0)
        selectedTag.setBackgroundResource(R.drawable.selected_tag)
        selectedTag.setTextColor(ResourcesCompat.getColor(resources, R.color.white, context?.theme))
        for (tag in tags){
            tag.setBackgroundResource(R.drawable.tag)
            tag.setTextColor(ResourcesCompat.getColor(resources, R.color.black, context?.theme))
        }
    }

    override fun onDishClick(id: Int) {
        val dialogFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putInt(ProductFragment.TAG_BUNDLE, id)
        dialogFragment.arguments = bundle
        fragmentManager?.let { dialogFragment.show(it, ProductFragment.TAG) }
    }
}