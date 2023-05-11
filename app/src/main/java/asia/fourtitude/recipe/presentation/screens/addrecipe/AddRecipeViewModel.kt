package asia.fourtitude.recipe.presentation.screens.addrecipe

import androidx.lifecycle.viewModelScope
import asia.fourtitude.recipe.BaseViewModel
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.data.RecipeApi
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.triggered
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val recipeApi: RecipeApi,
    private val repository: RecipeRepository,
) : BaseViewModel() {

    private val _viewState = MutableStateFlow(AddRecipeViewState())
    val viewState = _viewState.asStateFlow()

    fun getSearchItems(query: String, onCompleteRequest : (Boolean) -> Unit) {
        viewModelScope.launch {
            _viewState.update { currentState -> currentState.copy(isLoadingItems = true) }
            delay(200)
            val response = recipeApi.searchPhotos(
                query = query,
                apiKey = "Ah7wsxC30WrFjp13UbOBQJYPeLF0Wyj1nMmIeeVXm7E"
            )
            if (response.isSuccessful) {
                _viewState.update { currentState ->
                    currentState.copy(
                        photos = response.body()?.results.orEmpty(),
                        downloadSucceededEvent = triggered,
                        isLoadingItems = false
                    )
                }
                onCompleteRequest(response.body()?.results.orEmpty().isNotEmpty())
            } else {
                _viewState.update { currentState ->
                    currentState.copy(
                        downloadFailedEvent = triggered(R.string.main_error_load_items),
                        isLoadingItems = false
                    )
                }
                onCompleteRequest(response.body()?.results.orEmpty().isNotEmpty())
            }
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

}

