package com.example.myapplication.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Product
import com.example.myapplication.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val products: List<Product>) : UiState()
        object Error : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val result = repository.getAllProducts()
                _state.value = UiState.Success(result.products)

            } catch (e: Exception) {
                _state.value = UiState.Error
            }
        }
    }
}
