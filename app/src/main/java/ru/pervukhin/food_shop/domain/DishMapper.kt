package ru.pervukhin.food_shop.domain

class DishMapper {
    companion object{
        fun dishToCart(dish: Dish) = CartDish(dish.id, dish.name, dish.price, dish.weight, dish.imageUrl, 1)
    }
}