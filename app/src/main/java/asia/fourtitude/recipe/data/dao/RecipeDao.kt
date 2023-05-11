package asia.fourtitude.recipe.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import asia.fourtitude.recipe.data.model.recipe.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT COUNT(*) FROM recipes")
    suspend fun getRecipesCount(): Int

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Long): Recipe?
}