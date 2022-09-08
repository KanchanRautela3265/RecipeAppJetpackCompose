package com.compose.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.ui.RecipeViewModel

@Composable
fun Navigation(navController: NavHostController,viewModel: RecipeViewModel){
    NavHost(navController= navController, startDestination = "splash"){
        composable(route = "splash"){
            Splash(navController)
        }
        composable(route = "recipe"){
            Recipe(navController,viewModel)
        }
        composable(route = "recipe_details"){
            RecipeDetails(navController,viewModel = viewModel)
        }
    }

}