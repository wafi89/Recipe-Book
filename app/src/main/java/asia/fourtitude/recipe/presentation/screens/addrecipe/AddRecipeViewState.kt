package asia.fourtitude.recipe.presentation.screens.addrecipe

import asia.fourtitude.recipe.data.model.search.Photo
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class AddRecipeViewState(
    val photos: List<Photo> = emptyList(),
    val isLoadingItems: Boolean = false,
    val downloadSucceededEvent: StateEvent = consumed,
    val downloadFailedEvent: StateEventWithContent<Int> = consumed()
)