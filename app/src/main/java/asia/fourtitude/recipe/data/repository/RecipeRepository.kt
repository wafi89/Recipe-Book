package asia.fourtitude.recipe.data.repository

import asia.fourtitude.recipe.data.dao.RecipeDao
import asia.fourtitude.recipe.data.model.recipe.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    val recipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun getRecipesCount(): Int {
        return recipeDao.getRecipesCount()
    }

    suspend fun getRecipeById(recipeId: Long): Recipe? {
        return recipeDao.getRecipeById(recipeId)
    }
}