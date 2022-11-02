package com.example.shoppingapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.R
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.mapper.ProductMapper
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.model.product.ProductDTO
import com.example.shoppingapp.data.repository.ProductsRepository
import com.example.shoppingapp.utils.UiState
import com.example.shoppingapp.utils.apiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductsRepository

) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Product>>?>()
    val uiState: LiveData<UiState<List<Product>>?> = _uiState
    private val mapper = ProductMapper()
    private val _uiEvent = MutableSharedFlow<PostViewEvent>()
    val uiEvent: SharedFlow<PostViewEvent> = _uiEvent

    init {
        getPosts()
    }

    fun addCart(product: ProductDTO){
        viewModelScope.launch {
            val productEntity: ProductEntity = mapper.mapToEntity(product)
            productRepository.addProduct(productEntity)
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
           _uiState.value= apiCall(R.string.generic_error_message){
                productRepository.getAllProducts()
            }
        }
    }

     fun onFavoritePost(product: ProductDTO) {
        product.id.let { safePostId ->
            productRepository.getPostById(safePostId)?.let {
                productRepository.deleteAddCart(it)

            } ?: kotlin.run {
                productRepository.insertAddCart(
                    ProductEntity(
                        id = product.id.toLong(),
                        title = product.title,
                        price = product.price,
                        category = product.category,
                        description = product.description.toString(),
                        image = product.image

                    )
                )
            }
        }
    }


}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}



