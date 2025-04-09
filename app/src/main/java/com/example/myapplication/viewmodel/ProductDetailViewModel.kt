package com.example.myapplication.viewmodel

import android.util.Log
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
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val product: Product) : UiState()
        object Error : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    fun loadProduct(id: Int) {
        Log.d("ProductVM", "loadProduct called with id = $id")
        _state.value = UiState.Loading
        viewModelScope.launch {
            try {
                val result = repository.getProductById(id)
                Log.d("ProductVM", "Product fetched successfully: $result")
                _state.value = UiState.Success(result)
            } catch (e: Exception) {
                Log.e("ProductVM", "Error fetching product: ${e.message}", e)
                _state.value = UiState.Error
            }
        }
    }
}
