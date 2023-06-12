package ru.pervukhin.food_shop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.pervukhin.food_shop.data.database.AppDataBase
import ru.pervukhin.food_shop.data.database.CartDishDao
import ru.pervukhin.food_shop.data.database.RoomRepository
import ru.pervukhin.food_shop.domain.CartDishRepository
import javax.inject.Singleton

@Module
class RoomModule(val context: Context) {


    @Singleton
    @Provides
    fun provideCartDishRepository(): CartDishRepository{
        return RoomRepository()
    }

    @Singleton
    @Provides
    fun provideCartDishDao(appDataBase: AppDataBase): CartDishDao {
        return appDataBase.getCartDishDao()
    }

    @Singleton
    @Provides
    fun provideAppDataBase(): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java,"dataBase.db").build()
    }
}