package com.example.myapplication.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screen.ProductDetailScreen
import com.example.myapplication.screen.ProductListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen(navController, hiltViewModel())
        }
        composable("product_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ProductDetailScreen(navController, id, hiltViewModel())
        }
    }
}