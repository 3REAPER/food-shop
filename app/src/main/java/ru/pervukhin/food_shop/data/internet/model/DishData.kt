package ru.pervukhin.food_shop.data.internet.model

import com.google.gson.annotations.SerializedName

class DishData(
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
    val tags: List<String>?){

    override fun equals(other: Any?): Boolean {
        return other is DishData && other.id == id && other.name == name && other.price == price && other.weight == weight && other.description == description && other.imageUrl == imageUrl && other.tags == tags
    }

    override fun hashCode(): Int {
        var result = 0
        id?.let {
            result = id
        }
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + tags.hashCode()
        return result
    }
}