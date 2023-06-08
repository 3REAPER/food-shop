package ru.pervukhin.food_shop

import android.app.Application
import ru.pervukhin.food_shop.di.AppComponent
import ru.pervukhin.food_shop.di.DaggerAppComponent
import ru.pervukhin.food_shop.di.RetrofitModule

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}