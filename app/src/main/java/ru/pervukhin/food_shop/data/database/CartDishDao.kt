package ru.pervukhin.food_shop.data.database

import androidx.room.*

@Dao
interface CartDishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cartDishEntity: CartDishEntity)

    @Query("UPDATE cartDishEntity SET count = count + 1 WHERE id=:id")
    fun plusOne(id: Int)

    @Query("UPDATE cartDishEntity SET count = count - 1 WHERE id=:id")
    fun minusOne(id: Int)

    @Query("SELECT * FROM cartDishEntity")
    fun getAll(): List<CartDishEntity>

    @Query("SELECT * FROM cartDishEntity WHERE id= :id")
    fun getById(id: Int): CartDishEntity

    @Query("DELETE FROM cartDishEntity WHERE id= :id")
    fun delete(id: Int)
}