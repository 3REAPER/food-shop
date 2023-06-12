package ru.pervukhin.food_shop.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.food_shop.App
import ru.pervukhin.food_shop.domain.CartDish
import ru.pervukhin.food_shop.domain.CartDishRepository
import javax.inject.Inject

class CartViewModel : ViewModel() {
    val cartDishLiveData: MutableLiveData<CartDishState> = MutableLiveData()
    @Inject
    lateinit var cartDishRepository: CartDishRepository

    init {
        App.appComponent.inject(this)
    }

    fun getAll(){
        cartDishLiveData.value = CartDishState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            cartDishRepository.getAll().let {
                if (it.isNotEmpty()){
                    launch(Dispatchers.Main) {
                        cartDishLiveData.value = CartDishState.Success(it)
                    }
                }else{
                    launch(Dispatchers.Main) {
                        cartDishLiveData.value = CartDishState.Empty
                    }
                }
            }
        }
    }

    fun removeCartDishById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            cartDishRepository.removeCartDishById(id)
        }
    }

    fun plusCountDish(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            cartDishRepository.plusCountDish(id)
        }
    }

    fun minusCountDish(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            cartDishRepository.minusCountDish(id)
        }
    }

    sealed class CartDishState{
        object Loading: CartDishState()
        class Success(val cartDishList: List<CartDish>): CartDishState()
        object Empty: CartDishState()
    }
}