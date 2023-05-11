package asia.fourtitude.recipe.presentation.screens.appbar

import asia.fourtitude.recipe.BaseViewModel
import asia.fourtitude.recipe.data.RecipeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainTopBarViewModel @Inject constructor(
    private val recipeApi: RecipeApi
) : BaseViewModel()