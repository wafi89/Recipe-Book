package asia.fourtitude.recipe.presentation.screens.main

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class MainViewState(
    val items: List<String> = emptyList(),
    val isLoadingItems: Boolean = false,
    val downloadSucceededEvent: StateEvent = consumed,
    val downloadFailedEvent: StateEventWithContent<Int> = consumed()
)