package ru.pervukhin.food_shop.di

import dagger.Module
import dagger.Provides
import ru.pervukhin.food_shop.data.ProfileRepositoryImpl
import ru.pervukhin.food_shop.domain.ProfileRepository
import javax.inject.Singleton

@Module
class LocationModule {

    @Singleton
    @Provides
    fun provideProfileRepository(): ProfileRepository{
        return ProfileRepositoryImpl()
    }
}