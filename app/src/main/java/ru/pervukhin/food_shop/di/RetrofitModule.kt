package ru.pervukhin.food_shop.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.food_shop.data.internet.DishesService
import ru.pervukhin.food_shop.data.internet.InternetConnectionImpl
import ru.pervukhin.food_shop.data.internet.RetrofitRepository
import ru.pervukhin.food_shop.domain.DishRepository
import ru.pervukhin.food_shop.domain.InternetConnection
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideInternetConnection(): InternetConnection {
        return InternetConnectionImpl()
    }

    @Provides
    @Singleton
    fun provideDishesRepository(): DishRepository {
        return RetrofitRepository()
    }

    @Provides
    @Singleton
    fun provideDishesService(retrofit: Retrofit): DishesService {
        return retrofit.create(DishesService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}