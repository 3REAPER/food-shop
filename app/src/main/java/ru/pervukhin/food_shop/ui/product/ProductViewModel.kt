package ru.pervukhin.food_shop.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.data.retrofit.DishesService
import ru.pervukhin.food_shop.domain.Dishes
import ru.pervukhin.food_shop.domain.DishesRepository
import javax.inject.Inject

class ProductViewModel : ViewModel() {
    val liveData : MutableLiveData<Dishes> = MutableLiveData()
    @Inject
    lateinit var dishesRepository: DishesRepository

    init {
        App.appComponent.inject(this)
    }

    fun getDishesById(id: Int){
        viewModelScope.launch {
            liveData.value = dishesRepository.getById(id)
        }
    }
}