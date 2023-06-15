package ru.pervukhin.food_shop.data.internet.model

import com.google.gson.annotations.SerializedName

class DishesList(
  @SerializedName("dishes")
  val dishes: List<DishData>
)