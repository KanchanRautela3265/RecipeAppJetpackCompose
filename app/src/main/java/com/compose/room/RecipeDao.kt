package com.compose.room

import androidx.room.*
import com.compose.ui.RecipeDto
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("Select * from RecipeTable")
    fun getAllRecipe(): Flow<List<RecipeDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeInDb(recipeDtoList: List<RecipeDto>)

    @Query("DELETE FROM RecipeTable")
    suspend fun deleteAllDataFromDB()
}