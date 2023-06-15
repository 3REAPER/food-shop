package ru.pervukhin.food_shop.data.internet

import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishRepository
import javax.inject.Inject

class RetrofitRepository: DishRepository {
    @Inject
    lateinit var dishesService: DishesService

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getAll(): List<Dish> {
        dishesService.getDishes().let {
            if (it.isSuccessful) {
                it.body()?.let { dishesList ->
                    return DishDataMapper.dataListToDomainList(dishesList.dishes)
                }
            }
            return listOf()
        }

    }

    override suspend fun getByTag(tag: String): List<Dish> {
        var result: List<Dish> = listOf()
        dishesService.getDishes().let {
            if (it.isSuccessful) {
                it.body()?.let {dishesListNotNull ->
                    for (dish in dishesListNotNull.dishes) {
                        DishDataMapper.dataToDomain(dish)?.let {
                            for (tagFind in it.tags) {
                                if (tag == tagFind){
                                    result = result.plus(it)
                                }
                            }
                        }

                    }
                    return result
                }
            }
        }
        return listOf()
    }

    override suspend fun getById(id: Int): Dish? {
        dishesService.getDishes().let { response ->
            if (response.isSuccessful) {
                response.body()?.let {dishesListNotNull ->
                    for (dish in dishesListNotNull.dishes) {
                        DishDataMapper.dataToDomain(dish)?.let {
                            if (it.id == id){
                                return it
                            }
                        }
                    }
                }
            }
        }
        return null
    }

}