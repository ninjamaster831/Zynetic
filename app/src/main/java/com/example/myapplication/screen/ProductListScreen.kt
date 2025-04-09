package com.example.myapplication.screen

import com.example.myapplication.viewmodel.ProductListViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductListViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is ProductListViewModel.UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
        is ProductListViewModel.UiState.Error -> Text("Error loading products")
        is ProductListViewModel.UiState.Success -> {
            val products = (state as ProductListViewModel.UiState.Success).products
            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                items(products) { product ->
                    Card(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("product_detail/${product.id}")
                        }) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(product.thumbnail),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                                Text(text = product.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                }
            }
        }
    }
}
