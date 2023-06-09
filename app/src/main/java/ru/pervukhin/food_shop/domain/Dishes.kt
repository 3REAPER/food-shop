package ru.pervukhin.food_shop.domain

class Dishes(val id: Int, val name: String, val price: Int, val weight: Int, val description: String, val imageUrl: String, val tags: List<String>, ) {

    companion object{
        const val TAG_AlL_DISHES = "Все меню"
        const val TAG_SALAD = "Салаты"
        const val TAG_WITH_RICE = "С рисом"
        const val TAG_WITH_FISH = "С рыбой"
        const val TAG_WITH_ROLLS = "Роллы"
    }
}