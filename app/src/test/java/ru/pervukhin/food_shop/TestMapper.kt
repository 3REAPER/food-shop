package ru.pervukhin.food_shop

import org.junit.Test

import org.junit.Assert.*
import ru.pervukhin.food_shop.data.database.CartDishEntity
import ru.pervukhin.food_shop.data.database.CartDishMapper
import ru.pervukhin.food_shop.data.internet.DishDataMapper
import ru.pervukhin.food_shop.data.internet.model.DishData
import ru.pervukhin.food_shop.domain.CartDish
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishMapper

class TestMapper {

    @Test
    fun testDishToCart() {
        val dish = Dish(1, "пельмени", 299, 500, "очень вкусно", "https://", listOf())
        val expected = CartDish(1, "пельмени", 299, 500, "https://", 1)
        assertEquals(expected, DishMapper.dishToCart(dish))
    }


    @Test
    fun testDomainToEntity() {
        val cardDish = CartDish(1, "пельмени", 299, 500, "очень вкусно", 1)
        val expected = CartDishEntity(1, "пельмени", 299, 500, "очень вкусно", 1)
        assertEquals(expected, CartDishMapper.domainToEntity(cardDish))
    }

    @Test
    fun testEntityToDomain() {
        val cardDishEntity = CartDishEntity(1, "пельмени", 299, 500, "очень вкусно", 1)
        val expected = CartDish(1, "пельмени", 299, 500, "очень вкусно", 1)
        assertEquals(expected, CartDishMapper.entityToDomain(cardDishEntity))
    }


    @Test
    fun testEntityListToDomainList() {
        val item1 = CartDishEntity(1, "пельмени", 299, 500, "очень вкусно", 1)
        val item2 = CartDishEntity(2, "пицца", 699, 800, "http://", 1)
        val expected: List<CartDish> = listOf(CartDish(1, "пельмени", 299, 500, "очень вкусно", 1), CartDish(2, "пицца", 699, 800, "http://", 1) )
        assertEquals(expected, CartDishMapper.entityListToDomainList(listOf(item1, item2)))
    }

    @Test
    fun testDataToDomain() {
        val cartDish = DishData(1, "пельмени", 299, 500, "очень вкусно", "http://", listOf())
        val expected = Dish(1, "пельмени", 299, 500, "очень вкусно", "http://", listOf())
        assertEquals(expected, DishDataMapper.dataToDomain(cartDish))
    }

    @Test
    fun testDataListToDomainList() {
        val item1 = DishData(1, "пельмени", 299, 500, "очень вкусно", "http://", listOf())
        val item2 = DishData(2, "пицца", 699, 800, "очень вкусно", "http://", listOf())
        val  expected: List<Dish> = listOf(Dish(1, "пельмени", 299, 500, "очень вкусно", "http://", listOf()), Dish(2, "пицца", 699, 800, "очень вкусно", "http://", listOf()))
        assertEquals(expected, DishDataMapper.dataListToDomainList(listOf(item1, item2)))
    }
}