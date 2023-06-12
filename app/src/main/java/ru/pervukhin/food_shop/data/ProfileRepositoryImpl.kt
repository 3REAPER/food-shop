package ru.pervukhin.food_shop.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.CancellationToken
import ru.pervukhin.food_shop.R
import ru.pervukhin.food_shop.domain.ProfileRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ProfileRepositoryImpl: ProfileRepository {

    override fun getDate(): String {
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)

    }


    override fun getProfileImage(): Int {
        return R.drawable.profile_image
    }
}