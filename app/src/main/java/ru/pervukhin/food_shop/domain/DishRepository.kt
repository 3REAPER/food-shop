package ru.pervukhin.food_shop.domain

interface DishRepository {

    suspend fun getAll(): List<Dish>

    suspend fun getByTag(tag: String): List<Dish>

    suspend fun getById(id: Int): Dish?
}