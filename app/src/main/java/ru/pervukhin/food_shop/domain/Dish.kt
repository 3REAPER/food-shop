package ru.pervukhin.food_shop.domain

class Dish(val id: Int, val name: String, val price: Int, val weight: Int, val description: String, val imageUrl: String, val tags: List<String>, ) {

    companion object{
        const val TAG_AlL_DISHES = "Все меню"
        const val TAG_SALAD = "Салаты"
        const val TAG_WITH_RICE = "С рисом"
        const val TAG_WITH_FISH = "С рыбой"
        const val TAG_WITH_ROLLS = "Роллы"
    }

    override fun equals(other: Any?): Boolean {
        return other is Dish && other.id == id && other.name == name && other.price == price && other.weight == weight && other.description == description && other.imageUrl == imageUrl && other.tags == tags
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + tags.hashCode()
        return result
    }
}