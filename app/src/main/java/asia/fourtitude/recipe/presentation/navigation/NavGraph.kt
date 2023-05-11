package asia.fourtitude.recipe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import asia.fourtitude.recipe.constant.Constants
import asia.fourtitude.recipe.presentation.screens.addrecipe.AddRecipeScreen
import asia.fourtitude.recipe.presentation.screens.recipe.RecipeScreen
import asia.fourtitude.recipe.presentation.screens.recipedetail.RecipeDetailScreen

@Composable
fun NavGraphMain(
    navController: NavHostController,
    isPromptDelete : MutableState<Boolean>,
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Recipe.route
    ) {

        composable(NavScreen.Recipe.route) {
            RecipeScreen(
                navController = navController,
            )
        }

        composable(NavScreen.AddRecipe.route) {
            AddRecipeScreen(
                navController = navController,
            )
        }

        composable(
            NavScreen.RecipeDetail.route,
            listOf(
                navArgument(Constants.RECIPE_DETAILS_ARGUMENT_KEY_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getLong(Constants.RECIPE_DETAILS_ARGUMENT_KEY_ID)
                ?.let {
                    RecipeDetailScreen(
                        navController = navController,
                        isPromptDelete = isPromptDelete,
                    )
                }
        }

    }
}