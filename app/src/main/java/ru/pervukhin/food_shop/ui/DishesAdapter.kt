package ru.pervukhin.food_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.Dishes

class DishesAdapter() : RecyclerView.Adapter<DishesAdapter.DishesViewHolder>() {
    private var list: List<Dishes> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dishes, parent, false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val layout1 = holder.itemView.findViewById<ConstraintLayout>(R.id.layout1)
        val imageView1 = holder.itemView.findViewById<ImageView>(R.id.dishes1)
        val title1 = holder.itemView.findViewById<TextView>(R.id.title1)
        val layout2 = holder.itemView.findViewById<ConstraintLayout>(R.id.layout2)
        val imageView2 = holder.itemView.findViewById<ImageView>(R.id.dishes2)
        val title2 = holder.itemView.findViewById<TextView>(R.id.title2)
        val layout3 = holder.itemView.findViewById<ConstraintLayout>(R.id.layout3)
        val imageView3 = holder.itemView.findViewById<ImageView>(R.id.dishes3)
        val title3 = holder.itemView.findViewById<TextView>(R.id.title3)

        val k = position * 3
        if (k < list.size) {
            title1.text = list[k].name
            Glide.with(holder.itemView).load(list[k].imageUrl).into(imageView1)
        }else{
            layout1.visibility = View.INVISIBLE
        }
        if (k + 1 < list.size) {
            title2.text = list[k + 1].name
            Glide.with(holder.itemView).load(list[k + 1].imageUrl).into(imageView2)
        }else{
            layout2.visibility = View.INVISIBLE
        }
        if (k + 2 < list.size) {
            title3.text = list[k + 2].name
            Glide.with(holder.itemView).load(list[k + 2].imageUrl).into(imageView3)
        }else{
            layout3.visibility = View.INVISIBLE
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Dishes>){
        this.list = list
        notifyDataSetChanged()
    }

    class DishesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}