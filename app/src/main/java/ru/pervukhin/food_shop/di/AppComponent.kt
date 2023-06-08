package ru.pervukhin.food_shop.di

import dagger.Component
import retrofit2.Retrofit
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.data.retrofit.DishesService
import ru.pervukhin.food_shop.data.retrofit.ImageService
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun retrofit(): Retrofit
    fun dishesService(): DishesService
    fun imageService(): ImageService
}