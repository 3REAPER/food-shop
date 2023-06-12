package ru.pervukhin.food_shop.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.data.database.CartDishMapper
import ru.pervukhin.food_shop.domain.CartDishRepository
import ru.pervukhin.food_shop.domain.Dish
import ru.pervukhin.food_shop.domain.DishMapper
import ru.pervukhin.food_shop.domain.DishRepository
import javax.inject.Inject

class ProductViewModel : ViewModel() {
    val liveData : MutableLiveData<ProductState> = MutableLiveData()
    @Inject
    lateinit var dishRepository: DishRepository
    @Inject
    lateinit var cartDishRepository: CartDishRepository

    init {
        App.appComponent.inject(this)
    }

    fun getDishesById(id: Int){
        liveData.value = ProductState.Loading
        viewModelScope.launch {
            dishRepository.getById(id).let {
                if (it != null){
                    liveData.value = ProductState.Success(it)
                }else{
                    liveData.value = ProductState.Error
                }
            }
        }
    }

    fun addToCart(dish: Dish){
        viewModelScope.launch(Dispatchers.IO) {
            cartDishRepository.addCartDish(DishMapper.dishToCart(dish))
        }
    }

    sealed class ProductState {
        data class Success(val dish: Dish): ProductState()
        object Error: ProductState()
        object Loading: ProductState()
    }}