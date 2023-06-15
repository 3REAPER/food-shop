package ru.pervukhin.food_shop.domain

class CartDish(val id: Int, val name: String, val price: Int, val weight: Int, val imageUrl: String, var count: Int){

    override fun equals(other: Any?): Boolean {
        return other is CartDish && other.id == id && other.name == name && other.price == price && other.weight == weight && other.imageUrl == imageUrl && other.count == count
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + count.hashCode()
        return result
    }
}