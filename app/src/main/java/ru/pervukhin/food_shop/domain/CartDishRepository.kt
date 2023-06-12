package ru.pervukhin.food_shop.domain

interface CartDishRepository {

    suspend fun getAll(): List<CartDish>

    suspend fun removeCartDishById(id: Int)

    suspend fun addCartDish(cartDish: CartDish)

    suspend fun plusCountDish(id: Int)

    suspend fun minusCountDish(id: Int)
}