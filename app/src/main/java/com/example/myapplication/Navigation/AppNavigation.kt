package com.example.myapplication.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screen.ProductDetailScreen
import com.example.myapplication.screen.ProductListScreen
import com.example.myapplication.viewmodel.ProductDetailViewModel
import com.example.myapplication.viewmodel.ProductListViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "product_list") {
        composable("product_list") {
            val viewModel: ProductListViewModel = hiltViewModel()
            ProductListScreen(navController, viewModel)
        }
        composable("product_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            val viewModel: ProductDetailViewModel = hiltViewModel()
            ProductDetailScreen(navController, id, viewModel)
        }
    }
}
