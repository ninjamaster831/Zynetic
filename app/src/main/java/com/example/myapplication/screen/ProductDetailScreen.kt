package com.example.myapplication.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailScreen(navController: NavController, id: Int, viewModel: ProductDetailViewModel) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(id) { viewModel.loadProduct(id) }

    when (state) {
        is ProductDetailViewModel.UiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is ProductDetailViewModel.UiState.Error -> Text("Error loading product")

        is ProductDetailViewModel.UiState.Success -> {
            val product = (state as ProductDetailViewModel.UiState.Success).product
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

                Image(
                    painter = rememberAsyncImagePainter(product.thumbnail),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp)
                )

                Text(product.title, style = MaterialTheme.typography.headlineSmall)
                Text("Category: ${product.category}", style = MaterialTheme.typography.bodyMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                    }
                    Text("  ${product.rating}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("₹${product.price}", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(product.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
