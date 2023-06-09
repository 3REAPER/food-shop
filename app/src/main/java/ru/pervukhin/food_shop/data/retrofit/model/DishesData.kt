package ru.pervukhin.food_shop.data.retrofit.model

import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

class DishesData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("weight")
    val weight: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("tegs")
    val tags: List<String>?)