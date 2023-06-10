package ru.pervukhin.food_shop.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.Category

class ProductFragment : DialogFragment() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_product, container, false)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val dishesImage = fragment.findViewById<ImageView>(R.id.dishes_image)
        val name = fragment.findViewById<TextView>(R.id.name)
        val price = fragment.findViewById<TextView>(R.id.price)
        val weight = fragment.findViewById<TextView>(R.id.weight)
        val description = fragment.findViewById<TextView>(R.id.description)
        val addToCart = fragment.findViewById<TextView>(R.id.add)

        arguments?.let {
            viewModel.getDishesById(it.getInt(TAG_BUNDLE))
        }

        viewModel.liveData.observe(viewLifecycleOwner){
            it?.let {
                name.text = it.name
                price.setTextPrice(it.price.toString())
                weight.setTextWeight(it.weight.toString())
                description.text = it.description

                addToCart.setOnClickListener {

                }

                Glide.with(fragment).load(it.imageUrl).into(dishesImage)
            }
        }

        return fragment
    }

    companion object {
        const val TAG_BUNDLE = "idDishes"
        const val TAG = "DishesFragment"
    }

    private fun TextView.setTextPrice(text: String){
        this.text = text + " ₽"
    }


    private fun TextView.setTextWeight(text: String){
        this.text = text + "г"
    }
}