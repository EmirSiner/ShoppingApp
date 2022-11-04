package com.example.shoppingapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.CartEntity
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.repository.cart.CartRepository
import com.example.shoppingapp.data.repository.product.ProductRepositoryImpl
import com.example.shoppingapp.data.repository.product.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel  @Inject constructor(
    private val dbRepository:CartRepository
): ViewModel() {
    val basketList: MutableLiveData<List<Product>> = MutableLiveData()
    val progressBar = MutableLiveData<Boolean>()


    fun getAllProductFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            progressBar.postValue(true)

        }
    }

    fun deleteProduct(cartEntity: CartEntity){
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.delete(cartEntity)
            getAllProductFromRoom()
        }
    }
}