package ru.pervukhin.food_shop.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.food_shop.R

class CartFragment : Fragment(), OnClickPlusMinusListener {
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val loading = view.findViewById<FrameLayout>(R.id.loading)
        val empty = view.findViewById<TextView>(R.id.empty)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = CartAdapter(this)

        recyclerView.adapter = adapter
        viewModel.getAll()

        viewModel.cartDishLiveData.observe(viewLifecycleOwner){
            when(it){
                CartViewModel.CartDishState.Loading ->{
                    loading.visibility = View.VISIBLE
                }
                is CartViewModel.CartDishState.Success ->{
                    loading.visibility = View.GONE
                    adapter.setList(it.cartDishList)
                }
                CartViewModel.CartDishState.Empty->{
                    loading.visibility = View.GONE
                    empty.visibility = View.VISIBLE
                }
            }
        }

        return view
    }

    override fun onClickPlus(id: Int) {
        viewModel.plusCountDish(id)
    }

    override fun onClickMinus(id: Int) {
        viewModel.minusCountDish(id)
    }

    override fun onZeroCount(id: Int) {
        viewModel.removeCartDishById(id)
    }
}