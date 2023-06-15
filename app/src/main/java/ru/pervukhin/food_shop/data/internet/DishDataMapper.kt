package ru.pervukhin.food_shop.data.internet

import ru.pervukhin.food_shop.data.internet.model.DishData
import ru.pervukhin.food_shop.domain.Dish

class DishDataMapper {
    companion object{
        fun dataToDomain(dishData: DishData) : Dish? {
            return if (dishData.id != null && dishData.name != null && dishData.price != null && dishData.weight != null && dishData.description != null && dishData.imageUrl != null && dishData.tags != null){
                Dish(dishData.id,
                    dishData.name,
                    dishData.price,
                    dishData.weight,
                    dishData.description,
                    dishData.imageUrl,
                    dishData.tags)
            }else{
                null
            }
        }

        fun dataListToDomainList(dishesList: List<DishData>): List<Dish>{
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