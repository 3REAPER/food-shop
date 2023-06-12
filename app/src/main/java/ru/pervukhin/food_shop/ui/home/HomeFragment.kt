package ru.pervukhin.food_shop.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.Category

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bread = view.findViewById<MaterialCardView>(R.id.bread)
        val fastFood = view.findViewById<MaterialCardView>(R.id.fast_food)
        val asianKitchen = view.findViewById<MaterialCardView>(R.id.asian_kitchen)
        val soups = view.findViewById<MaterialCardView>(R.id.soups)

        val activity = (activity as MainActivity)
        val bundle = Bundle()

        bread.setOnClickListener {
            bundle.putInt(Category.TAG, Category.BREAD)
            activity.navigateToCategory(bundle)
        }

        fastFood.setOnClickListener {
            bundle.putInt(Category.TAG, Category.FAST_FOOD)
            activity.navigateToCategory(bundle)
        }

        asianKitchen.setOnClickListener {
            bundle.putInt(Category.TAG, Category.ASIAN_KITCHEN)
            activity.navigateToCategory(bundle)
        }

        soups.setOnClickListener {
            bundle.putInt(Category.TAG, Category.SOUPS)
            activity.navigateToCategory(bundle)
        }

        return view
    }
}
