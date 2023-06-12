package ru.pervukhin.food_shop.data.internet

import retrofit2.Response
import retrofit2.http.GET
import ru.pervukhin.food_shop.data.internet.model.DishesList

interface DishesService {
    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Response<DishesList>
}