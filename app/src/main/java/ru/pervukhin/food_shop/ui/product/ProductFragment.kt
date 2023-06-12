package ru.pervukhin.food_shop.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        val loading = fragment.findViewById<FrameLayout>(R.id.loading)
        val backgroundImage = fragment.findViewById<FrameLayout>(R.id.background_image)
        val dishesImage = fragment.findViewById<ImageView>(R.id.dishes_image)
        val name = fragment.findViewById<TextView>(R.id.name)
        val priceAndWeight = fragment.findViewById<LinearLayout>(R.id.price_and_weight)
        val price = fragment.findViewById<TextView>(R.id.price)
        val weight = fragment.findViewById<TextView>(R.id.weight)
        val description = fragment.findViewById<TextView>(R.id.description)
        val addToCart = fragment.findViewById<TextView>(R.id.add)

        arguments?.let {
            viewModel.getDishesById(it.getInt(TAG_BUNDLE))
        }

        viewModel.liveData.observe(viewLifecycleOwner){
            when(it) {
                ProductViewModel.ProductState.Loading -> {
                    loading.visibility = View.VISIBLE
                    backgroundImage.visibility = View.GONE
                    name.visibility = View.GONE
                    priceAndWeight.visibility = View.GONE
                    description.visibility = View.GONE
                    addToCart.visibility = View.GONE
                }
                is ProductViewModel.ProductState.Success -> {
                    loading.visibility = View.GONE
                    backgroundImage.visibility = View.VISIBLE
                    name.visibility = View.VISIBLE
                    priceAndWeight.visibility = View.VISIBLE
                    description.visibility = View.VISIBLE
                    addToCart.visibility = View.VISIBLE

                    name.text = it.dish.name
                    price.setTextPrice(it.dish.price.toString())
                    weight.setTextWeight(it.dish.weight.toString())
                    description.text = it.dish.description

                    addToCart.setOnClickListener {view ->
                        viewModel.addToCart(it.dish)
                        dismiss()
                    }

                    Glide.with(fragment).load(it.dish.imageUrl).into(dishesImage)
                }
                ProductViewModel.ProductState.Error -> {
                    Toast.makeText(context, getString(R.string.went_wrong), Toast.LENGTH_LONG).show()
                    dismiss()

                }
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