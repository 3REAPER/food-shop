package ru.pervukhin.food_shop.data.internet

import ru.pervukhin.food_shop.data.internet.model.DishesData
import ru.pervukhin.food_shop.domain.Dish

class DishesMapper {
    companion object{
        fun dataToDomain(dishesData: DishesData) : Dish? {
            return if (dishesData.id != null && dishesData.name != null && dishesData.price != null && dishesData.weight != null && dishesData.description != null && dishesData.imageUrl != null && dishesData.tags != null){
                Dish(dishesData.id,
                    dishesData.name,
                    dishesData.price,
                    dishesData.weight,
                    dishesData.description,
                    dishesData.imageUrl,
                    dishesData.tags)
            }else{
                null
            }
        }

        fun dataListToDomainList(dishesList: List<DishesData>): List<Dish>{
            var result: List<Dish> = listOf()
            for (dishes in dishesList){
                if (dishes.imageUrl != null) {
                    dataToDomain(dishes)?.let {
                        result = result.plus(it)
                    }
                }
            }
            return result
        }
    }
}