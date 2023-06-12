package ru.pervukhin.food_shop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartDishEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCartDishDao(): CartDishDao
}