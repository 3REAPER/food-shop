package ru.pervukhin.food_shop.domain

interface DishesRepository {

    suspend fun getAll(): List<Dishes>

    suspend fun getByTag(tag: String): List<Dishes>

    suspend fun getById(id: Int): Dishes?
}