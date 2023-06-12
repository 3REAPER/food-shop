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
import ru.pervukhin.food_shop.domain.CartDish

class CartFragment : Fragment(), OnClickPlusMinusListener {
    private lateinit var viewModel: CartViewModel
    private lateinit var buy: TextView
    private lateinit var loading: FrameLayout
    private lateinit var empty: TextView
    private val adapter = CartAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        loading = view.findViewById(R.id.loading)
        empty = view.findViewById(R.id.empty)
        buy = view.findViewById(R.id.buy)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = adapter

        recyclerView.adapter = adapter
        viewModel.getAll()

        viewModel.cartDishLiveData.observe(viewLifecycleOwner){
            when(it){
                CartViewModel.CartDishState.Loading ->{
                    loading.visibility = View.VISIBLE
                }
                is CartViewModel.CartDishState.Success ->{
                    loading.visibility = View.GONE
                    adapter.list = it.cartDishList
                    buy.setTextPrice(adapter.list)

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
        buy.setTextPrice(adapter.list)
    }

    override fun onClickMinus(id: Int) {
        viewModel.minusCountDish(id)
        buy.setTextPrice(adapter.list)


    }

    override fun onZeroCount(id: Int) {
        viewModel.removeCartDishById(id)
        if (adapter.list.isEmpty()){
            loading.visibility = View.GONE
            empty.visibility = View.VISIBLE
            buy.setText(R.string.pay)
        }else{
            buy.setTextPrice(adapter.list)
        }
    }

    private fun TextView.setTextPrice(list: List<CartDish>){
        var price = 0
        for (cartDish in list){
            price += cartDish.price * cartDish.count
        }
        text = resources.getString(R.string.buy) + " " +price.toString() +" " +"₽"
    }

}