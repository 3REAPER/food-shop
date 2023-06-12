package ru.pervukhin.food_shop.ui.location

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.ProfileRepository
import javax.inject.Inject

class LocationViewModel : ViewModel() {
    @Inject
    lateinit var profileRepository: ProfileRepository

    init {
        App.appComponent.inject(this)
    }

    fun getDate():String {
        return profileRepository.getDate()
    }

    fun getResId(): Int{
        return profileRepository.getProfileImage()
    }
}