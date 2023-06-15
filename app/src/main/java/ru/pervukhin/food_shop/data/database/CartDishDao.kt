package ru.pervukhin.food_shop.data.database

import androidx.room.*

@Dao
interface CartDishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartDishEntity: CartDishEntity)

    @Query("UPDATE cartDishEntity SET count = count + 1 WHERE id=:id")
    suspend fun plusOne(id: Int)

    @Query("UPDATE cartDishEntity SET count = count - 1 WHERE id=:id")
    suspend fun minusOne(id: Int)

    @Query("SELECT * FROM cartDishEntity")
    suspend fun getAll(): List<CartDishEntity>

    @Query("SELECT * FROM cartDishEntity WHERE id= :id")
    suspend fun getById(id: Int): CartDishEntity

    @Query("DELETE FROM cartDishEntity WHERE id= :id")
    suspend fun delete(id: Int)
}