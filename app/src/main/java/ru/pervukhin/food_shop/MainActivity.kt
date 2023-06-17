package ru.pervukhin.food_shop

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.white, theme)
        }
        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bottomNavigationBar: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    navigationController.navigate(R.id.home)
                    return@setOnItemSelectedListener true
                }
                R.id.search -> {
                    navigationController.navigate(R.id.search)
                    return@setOnItemSelectedListener true
                }
                R.id.basket -> {
                    navigationController.navigate(R.id.basket)
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    navigationController.navigate(R.id.profile)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener true
            }
        }
    }

    fun navigateToCategory(bundle: Bundle){
        navigationController.navigate(R.id.category, bundle)
    }

    fun navigateToHome(){
        navigationController.navigate(R.id.home)
    }
}