package ru.pervukhin.food_shop.data.retrofit

import ru.pervukhin.food_shop.data.retrofit.model.DishesData
import ru.pervukhin.food_shop.domain.Dishes

class DishesMapper {
    companion object{
        fun dataToDomain(dishesData: DishesData) : Dishes? {
            return if (dishesData.id != null && dishesData.name != null && dishesData.price != null && dishesData.weight != null && dishesData.description != null && dishesData.imageUrl != null && dishesData.tags != null){
                Dishes(dishesData.id,
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

        fun dataListToDomainList(dishesList: List<DishesData>): List<Dishes>{
            var result: List<Dishes> = listOf()
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