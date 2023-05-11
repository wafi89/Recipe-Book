package asia.fourtitude.recipe.presentation.screens.recipe

import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import asia.fourtitude.recipe.BaseViewModel
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.data.repository.RecipeRepository
import asia.fourtitude.recipe.presentation.screens.recipe.data.getInitialRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : BaseViewModel() {

    val recipes: Flow<List<Recipe>> = repository.recipes.asLiveData().asFlow()

    fun initializeRecipes() {
        viewModelScope.launch {
            // Check if the database is empty
            val recipesCount = repository.getRecipesCount()
            if (recipesCount == 0) {
                // Prepopulate the database with initial recipes
                val initialRecipes = getInitialRecipes()
                initialRecipes.forEach { recipe ->
                    repository.addRecipe(recipe)
                }
            }
        }
    }

}