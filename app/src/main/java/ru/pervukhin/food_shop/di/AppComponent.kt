package ru.pervukhin.food_shop.di

import dagger.Component
import retrofit2.Retrofit
import ru.pervukhin.food_shop.data.database.CartDishDao
import ru.pervukhin.food_shop.data.database.RoomRepository
import ru.pervukhin.food_shop.data.internet.DishesService
import ru.pervukhin.food_shop.data.internet.ImageService
import ru.pervukhin.food_shop.data.internet.RetrofitRepository
import ru.pervukhin.food_shop.domain.CartDishRepository
import ru.pervukhin.food_shop.domain.DishRepository
import ru.pervukhin.food_shop.domain.ProfileRepository
import ru.pervukhin.food_shop.ui.cart.CartViewModel
import ru.pervukhin.food_shop.ui.category.CategoryViewModel
import ru.pervukhin.food_shop.ui.location.LocationFragment
import ru.pervukhin.food_shop.ui.location.LocationViewModel
import ru.pervukhin.food_shop.ui.product.ProductViewModel
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, RoomModule::class, LocationModule::class])
@Singleton
interface AppComponent {
    fun inject(categoryViewModel: CategoryViewModel)
    fun inject(repositoryRetrofit: RetrofitRepository)
    fun inject(productViewModel: ProductViewModel)
    fun inject(roomRepository: RoomRepository)
    fun inject(cartViewModel: CartViewModel)
    fun inject(locationViewModel: LocationViewModel)

    fun dishesRepository(): DishRepository
    fun retrofit(): Retrofit
    fun dishesService(): DishesService
    fun imageService(): ImageService

    fun cartDishRepository(): CartDishRepository
    fun cartDishDao(): CartDishDao

    fun profileRepository(): ProfileRepository
}