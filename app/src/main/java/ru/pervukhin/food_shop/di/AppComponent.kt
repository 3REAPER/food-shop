package ru.pervukhin.food_shop.di

import dagger.Component
import retrofit2.Retrofit
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.data.retrofit.DishesService
import ru.pervukhin.food_shop.data.retrofit.ImageService
import ru.pervukhin.food_shop.data.retrofit.RepositoryRetrofit
import ru.pervukhin.food_shop.domain.DishesRepository
import ru.pervukhin.food_shop.ui.category.CategoryFragment
import ru.pervukhin.food_shop.ui.category.CategoryViewModel
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(categoryViewModel: CategoryViewModel)
    fun inject(repositoryRetrofit: RepositoryRetrofit)

    fun dishesRepository(): DishesRepository
    fun retrofit(): Retrofit
    fun dishesService(): DishesService
    fun imageService(): ImageService
}