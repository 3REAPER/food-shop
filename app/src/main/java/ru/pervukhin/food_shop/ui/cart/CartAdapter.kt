package ru.pervukhin.food_shop.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.CartDish
import ru.pervukhin.food_shop.domain.Dish

class CartAdapter(val listener: OnClickPlusMinusListener) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var list: List<CartDish> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dishes = holder.itemView.findViewById<ImageView>(R.id.dishes)
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val price = holder.itemView.findViewById<TextView>(R.id.price)
        val weight = holder.itemView.findViewById<TextView>(R.id.weight)
        val minus = holder.itemView.findViewById<ImageView>(R.id.minus)
        val plus = holder.itemView.findViewById<ImageView>(R.id.plus)
        val count = holder.itemView.findViewById<TextView>(R.id.count)

        val cartDish = list[position]
        name.text = cartDish.name
        price.setTextPrice(cartDish.price.toString())
        weight.setTextWeight(cartDish.weight.toString())
        count.text = cartDish.count.toString()
        Glide.with(holder.itemView).load(cartDish.imageUrl).into(dishes)

        minus.setOnClickListener {
            if (count.text.toString().toInt() - 1 != 0) {
                listener.onClickMinus(cartDish.id)
                count.text = (count.text.toString().toInt() - 1).toString()
            }else{
                listener.onZeroCount(cartDish.id)
                list = list.minus(cartDish)
                notifyDataSetChanged()
            }
        }

        plus.setOnClickListener {
            if (count.text.toString().toInt() + 1 < 100) {
                listener.onClickPlus(cartDish.id)
                count.text = (count.text.toString().toInt() + 1).toString()
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<CartDish>){
        this.list = list
        notifyDataSetChanged()
    }

    private fun TextView.setTextPrice(text: String){
        this.text = text + " ₽"
    }


    private fun TextView.setTextWeight(text: String){
        this.text = text + "г"
    }

    class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}