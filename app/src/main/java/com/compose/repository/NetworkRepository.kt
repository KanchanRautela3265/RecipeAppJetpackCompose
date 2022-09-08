package com.compose.repository

import com.compose.room.RecipeDao
import com.compose.ui.ApiService
import javax.inject.Inject
import javax.inject.Named

class NetworkRepository @Inject constructor(
    @Named("auth_token") private val token: String,
    private val recipeDao: RecipeDao,
    private val apiService: ApiService
) {
    val flow = getRecipeData({ recipeDao.getAllRecipe() }, {
        apiService.search(token, 1, "")
    }, {
       recipeDao.insertRecipeInDb(it.recipes)
    })

}
