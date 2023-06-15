package ru.pervukhin.food_shop.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishRepository
import ru.pervukhin.food_shop.domain.InternetConnection
import javax.inject.Inject

class CategoryViewModel : ViewModel() {
    val repositoryLiveData : MutableLiveData<DishesState> = MutableLiveData()
    @Inject
    lateinit var dishRepository: DishRepository
    @Inject
    lateinit var internetConnection: InternetConnection

    init {
        App.appComponent.inject(this)
    }

    fun getAll(){
        repositoryLiveData.value = DishesState.Loading
        viewModelScope.launch {
            if (internetConnection.hasInternet()) {
                dishRepository.getAll().let {
                    if (it.isEmpty()) {
                        repositoryLiveData.value = DishesState.Empty
                    } else {
                        repositoryLiveData.value = DishesState.Success(it, Dish.TAG_AlL_DISHES)
                    }
                }
            }else{
                repositoryLiveData.value = DishesState.NoInternetConnection
            }
        }
    }

    fun getByTag(tag: String){
        repositoryLiveData.value = DishesState.Loading
        viewModelScope.launch {
            if (internetConnection.hasInternet()) {
                dishRepository.getByTag(tag).let {
                    if (it.isEmpty()) {
                        repositoryLiveData.value = DishesState.Empty
                    } else {
                        repositoryLiveData.value = DishesState.Success(it, tag)
                    }
                }
            }else{
                repositoryLiveData.value = DishesState.NoInternetConnection
            }
        }
    }

    sealed class DishesState {
        data class Success(val dishes: List<Dish>, val tag: String): DishesState()
        object Empty: DishesState()
        object Loading: DishesState()
        object NoInternetConnection: DishesState()
    }

}