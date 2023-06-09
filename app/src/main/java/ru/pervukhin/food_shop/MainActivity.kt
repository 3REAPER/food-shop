package ru.pervukhin.food_shop

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervukhin.food_shop.domain.Dishes

class MainActivity : AppCompatActivity() {
    lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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