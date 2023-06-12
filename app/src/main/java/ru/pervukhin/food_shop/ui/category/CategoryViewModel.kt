package ru.pervukhin.food_shop.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishRepository
import javax.inject.Inject

class CategoryViewModel : ViewModel() {
    val repositoryLiveData : MutableLiveData<DishesState> = MutableLiveData()
    @Inject
    lateinit var dishRepository: DishRepository

    init {
        App.appComponent.inject(this)
    }

    fun getAll(){
        repositoryLiveData.value = DishesState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            dishRepository.getAll().let {
                if (it.isEmpty()){
                    launch(Dispatchers.Main) {
                        repositoryLiveData.value = DishesState.Empty
                    }
                }else {
                    launch(Dispatchers.Main) {
                        repositoryLiveData.value = DishesState.Success(it, Dish.TAG_AlL_DISHES)
                    }
                }
            }
        }
    }

    fun getByTag(tag: String){
        repositoryLiveData.value = DishesState.Loading
        viewModelScope.launch {
            dishRepository.getByTag(tag).let {
                if (it.isEmpty()){
                    repositoryLiveData.value = DishesState.Empty
                }else{
                    repositoryLiveData.value = DishesState.Success(it, tag)
                }
            }
        }
    }

    sealed class DishesState {
        data class Success(val dishes: List<Dish>, val tag: String): DishesState()
        object Empty: DishesState()
        object Loading: DishesState()
    }

}