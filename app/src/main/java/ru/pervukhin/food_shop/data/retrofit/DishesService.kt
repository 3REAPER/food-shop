package ru.pervukhin.food_shop.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import ru.pervukhin.food_shop.data.retrofit.model.DishesData

interface DishesService {
    @GET("/v3/c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Response<List<DishesData>>
}