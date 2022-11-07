package com.example.shoppingapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.CategoryEntity
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.repository.category.CategoriesRepository
import com.example.shoppingapp.data.repository.product.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {


    private val _productSearchList = MutableStateFlow(emptyList<ProductEntity>())
    val productSearchList: StateFlow<List<ProductEntity>> = _productSearchList
    val categories = categoriesRepository.getAllCategoryEntities()

    init {
        viewModelScope.launch {
            productsRepository.getAllProductEntities()
                .collect { searchList ->
                    _productSearchList.value = searchList
                }
        }

        viewModelScope.launch {
            val itemCount = categoriesRepository.getCategoriesItemCount()
            if (itemCount == 0L) {
                val categories = categoriesRepository.getAllCategories()
                if (categories.isNotEmpty()) {
                    val categoryEntities = categories.map {
                        CategoryEntity(0L, it)
                    }
                    categoriesRepository.insertAllCategories(categoryEntities)
                }
            }
        }
    }

    fun filterByName(query: String) {
        viewModelScope.launch {
            productsRepository.filterProductByName(query)
                .collect { searchList ->
                    _productSearchList.value = searchList
                }
        }
    }

    // TODO: Use in fragment for category filter
    fun filterByCategory(category: String) {
        viewModelScope.launch {
            productsRepository.filterProductByCategory(category)
                .collect { searchList ->
                    _productSearchList.value = searchList
                }
        }
    }

}