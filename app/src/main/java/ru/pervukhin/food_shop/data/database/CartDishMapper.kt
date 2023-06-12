package ru.pervukhin.food_shop.data.database

import ru.pervukhin.food_shop.domain.CartDish

class CartDishMapper {
    companion object{
        private fun entityToDomain(cartDishEntity: CartDishEntity): CartDish {
            return CartDish(
                cartDishEntity.id,
                cartDishEntity.name,
                cartDishEntity.price,
                cartDishEntity.weight,
                cartDishEntity.imageUrl,
                cartDishEntity.count)
        }

        fun entityListToDomainList(cartDishEntityList: List<CartDishEntity>): List<CartDish> {
            var result: List<CartDish> = listOf()
            if (cartDishEntityList.isNotEmpty()){
                for (cartDishEntity in cartDishEntityList){
                    result = result.plus(entityToDomain(cartDishEntity))
                }
                return result
            }else{
                return listOf()
            }
        }

        fun domainToEntity(cartDish: CartDish): CartDishEntity {
            return CartDishEntity(
                cartDish.id,
                cartDish.name,
                cartDish.price,
                cartDish.weight,
                cartDish.imageUrl,
                cartDish.count)
        }
    }
}