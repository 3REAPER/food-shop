package ru.pervukhin.food_shop.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CartDishEntity(@PrimaryKey(autoGenerate = false) val id: Int, val name: String, val price: Int, val weight: Int, val imageUrl: String, val count: Int)