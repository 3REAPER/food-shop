package ru.pervukhin.food_shop.ui.category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.Category
import ru.pervukhin.food_shop.domain.Dishes
import ru.pervukhin.food_shop.ui.DishesAdapter

class CategoryFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        val title = view.findViewById<TextView>(R.id.title)
        val tagAllDishes = view.findViewById<ImageView>(R.id.all_dishes)
        val tagSalad = view.findViewById<ImageView>(R.id.salad)
        val tagWithRice = view.findViewById<ImageView>(R.id.with_rice)
        val tagWithFish = view.findViewById<ImageView>(R.id.with_fish)
        val tagRolls = view.findViewById<ImageView>(R.id.rolls)
        val back = view.findViewById<ImageView>(R.id.back)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = DishesAdapter()
        recyclerView.adapter = adapter

        viewModel.getAll()

        viewModel.repositoryLiveData.observe(viewLifecycleOwner){
            adapter.setList(it)
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
            viewModel.getByTag(Dishes.TAG_AlL_DISHES)
        }


        tagSalad.setOnClickListener {
            viewModel.getByTag(Dishes.TAG_SALAD)
        }


        tagWithRice.setOnClickListener {
            viewModel.getByTag(Dishes.TAG_WITH_RICE)
        }


        tagWithFish.setOnClickListener {
            viewModel.getByTag(Dishes.TAG_WITH_FISH)
        }


        tagRolls.setOnClickListener {
            viewModel.getByTag(Dishes.TAG_WITH_ROLLS)
        }

        back.setOnClickListener {
            (activity as MainActivity).navigateToHome()
        }

        return view
    }

}