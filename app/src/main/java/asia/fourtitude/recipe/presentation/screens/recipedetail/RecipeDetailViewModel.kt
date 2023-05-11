package asia.fourtitude.recipe.presentation.screens.recipedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import asia.fourtitude.recipe.BaseViewModel
import asia.fourtitude.recipe.constant.Constants
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe

    init {
        savedStateHandle.get<Long>(Constants.RECIPE_DETAILS_ARGUMENT_KEY_ID)?.let { id ->
            getRecipeById(id)
        }
    }

    private fun getRecipeById(recipeId: Long) {
        viewModelScope.launch {
            val recipe = repository.getRecipeById(recipeId)
            _selectedRecipe.value = recipe
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe, onDeleted : (Recipe?) -> Unit) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
            delay(300)
            val deletedRecipe = repository.getRecipeById(recipe.id)
            onDeleted(deletedRecipe)
        }
    }

}

