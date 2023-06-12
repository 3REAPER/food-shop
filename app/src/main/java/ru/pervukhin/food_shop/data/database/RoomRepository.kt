package ru.pervukhin.food_shop.data.database

import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.CartDish
import ru.pervukhin.food_shop.domain.CartDishRepository
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishMapper
import javax.inject.Inject

class RoomRepository: CartDishRepository {
    @Inject
    lateinit var cartDishDao: CartDishDao

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getAll(): List<CartDish> {
        return CartDishMapper.entityListToDomainList(cartDishDao.getAll())
    }

    override suspend fun removeCartDishById(id: Int) {
        cartDishDao.delete(id)
    }

    override suspend fun addCartDish(cartDish: CartDish) {
        cartDishDao.insert(CartDishMapper.domainToEntity(cartDish))
    }

    override suspend fun plusCountDish(id: Int) {
        cartDishDao.plusOne(id)
    }

    override suspend fun minusCountDish(id: Int) {
        cartDishDao.minusOne(id)
    }
}