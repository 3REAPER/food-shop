package ru.pervukhin.food_shop.data.retrofit.model

import com.google.gson.annotations.SerializedName

class DishesList(
  @SerializedName("dishes")
  val dishes: List<DishesData>
)