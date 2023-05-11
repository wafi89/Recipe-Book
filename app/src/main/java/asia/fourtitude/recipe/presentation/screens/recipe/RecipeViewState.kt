package asia.fourtitude.recipe.presentation.screens.recipe

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class RecipeViewState(
    val latestRecipes: List<String> = emptyList(),
    val isLoadingRoutes: Boolean = false,
    val downloadSucceededRoutes: StateEvent = consumed,
    val downloadFailedRoutes: StateEventWithContent<Int> = consumed()
)