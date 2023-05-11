package asia.fourtitude.recipe.presentation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavHostController

fun NavController.navigateTo(screenRoute: String) {
    navigate(screenRoute) {
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}