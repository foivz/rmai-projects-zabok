package com.example.kuharica.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>
}
