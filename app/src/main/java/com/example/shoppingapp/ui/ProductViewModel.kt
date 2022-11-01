package com.example.shoppingapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.R
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
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

    private val _uiState = MutableLiveData<UiState<List<ProductRecyclerViewItem.Product>>?>()
    val uiState: LiveData<UiState<List<ProductRecyclerViewItem.Product>>?> = _uiState

    private val _uiEvent = MutableSharedFlow<PostViewEvent>()
    val uiEvent: SharedFlow<PostViewEvent> = _uiEvent

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
           _uiState.value= apiCall(R.string.generic_error_message){
                productRepository.getAllProducts()
            }
        }
    }

    suspend fun onFavoritePost(product: ProductRecyclerViewItem.ProductDTO) {
        product.id.let { safePostId ->
            productRepository.getPostById(safePostId)?.let {
                productRepository.deleteAddCart(it)

            } ?: kotlin.run {
                productRepository.insertAddCart(
                    ProductEntity(
                       id = product.id.toLong(),
                        title = product.title,
                        price  = product.price,
                        category = product.category,
                        description = product.description.toString(),
                        image = product.image

                    )
                )

            }
        }
    }

    private suspend fun isExists(postId: Int?): Boolean {
        postId?.let {
            productRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }
}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}



