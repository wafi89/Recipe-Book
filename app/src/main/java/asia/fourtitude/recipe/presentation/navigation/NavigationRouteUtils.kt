package asia.fourtitude.recipe.presentation.navigation

import asia.fourtitude.recipe.R

object NavigationRouteUtils {
    fun getTitleFromNavRoute(route: String): Int {

        return when (route) {
            NavScreen.Recipe.route -> {
                R.string.top_app_bar_title_home
            }
            else -> R.string.top_app_bar_title_null
        }
    }
}