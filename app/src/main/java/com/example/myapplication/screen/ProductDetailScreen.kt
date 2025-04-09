package com.example.myapplication.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: Int,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Call only once
    LaunchedEffect(id) {
        viewModel.loadProduct(id)
    }

    when (state) {
        is ProductDetailViewModel.UiState.Loading -> Text("Loading...")
        is ProductDetailViewModel.UiState.Error -> Text("Error loading product")
        is ProductDetailViewModel.UiState.Success -> {
            val product = (state as ProductDetailViewModel.UiState.Success).product
            Column(modifier = Modifier.padding(16.dp)) {
                LazyRow(modifier = Modifier.height(200.dp)) {
                    items(product.images) { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(end = 8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(product.title, style = MaterialTheme.typography.headlineSmall)
                Text("Category: ${product.category}")
                Text("Price: ₹${product.price}")
                Text("Rating: ${product.rating}")
                Text(product.description)
            }
        }
    }
}
