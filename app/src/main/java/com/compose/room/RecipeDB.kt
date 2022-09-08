package com.compose.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.ui.RecipeDto
import javax.inject.Inject

@Database(entities = [RecipeDto::class], version = 1)
abstract class RecipeDB : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}