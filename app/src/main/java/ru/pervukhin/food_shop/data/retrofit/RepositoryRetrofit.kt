package ru.pervukhin.food_shop.data.retrofit

import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.Dishes
import ru.pervukhin.food_shop.domain.DishesRepository
import javax.inject.Inject

class RepositoryRetrofit: DishesRepository {
    @Inject
    lateinit var dishesService: DishesService

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getAll(): List<Dishes> {
        val dishes = dishesService.getDishes().body()
        if (dishesService.getDishes().isSuccessful) {
            dishes?.let {
                return DishesMapper.dataListToDomainList(dishes.dishes)
            }
        }
        return listOf()
    }

    override suspend fun getByTag(tag: String): List<Dishes> {
        var result: List<Dishes> = listOf()
        val dishesList = dishesService.getDishes().body()
        if (dishesService.getDishes().isSuccessful) {
            dishesList?.let {dishesListNotNull ->
                for (dish in dishesListNotNull.dishes) {
                    DishesMapper.dataToDomain(dish)?.let {
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
        return listOf()
    }

    override suspend fun getById(id: Int): Dishes? {
        val dishesList = dishesService.getDishes().body()
        if (dishesService.getDishes().isSuccessful) {
            dishesList?.let {dishesListNotNull ->
                for (dish in dishesListNotNull.dishes) {
                    DishesMapper.dataToDomain(dish)?.let {
                        if (it.id == id){
                            return it
                        }
                    }
                }
            }
        }
        return null
    }

}