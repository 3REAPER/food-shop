package ru.pervukhin.food_shop.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.data.retrofit.DishesService
import ru.pervukhin.food_shop.data.retrofit.RepositoryRetrofit
import ru.pervukhin.food_shop.domain.Dishes
import ru.pervukhin.food_shop.domain.DishesRepository
import javax.inject.Inject

class CategoryViewModel : ViewModel() {
    val repositoryLiveData : MutableLiveData<List<Dishes>> = MutableLiveData()
    @Inject
    lateinit var dishesRepository: DishesRepository

    init {
        App.appComponent.inject(this)
    }

    fun getAll(){
        viewModelScope.launch {
            repositoryLiveData.value = dishesRepository.getAll()
        }
    }

    fun getByTag(tag: String){
        viewModelScope.launch {
            repositoryLiveData.value = dishesRepository.getByTag(tag)
        }
    }

}