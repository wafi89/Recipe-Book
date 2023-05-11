package asia.fourtitude.recipe.presentation.navigation

sealed class NavScreen(var title: String, var route: String) {

    object Recipe : NavScreen("Recipe", "recipe")
    object AddRecipe : NavScreen("Add Recipe", "add_recipe")
    object RecipeDetail : NavScreen("Recipe Detail", "recipe_detail/{recipeId}"){
        fun passRecipeId(recipeId: Long) = "recipe_detail/$recipeId"
    }
}
