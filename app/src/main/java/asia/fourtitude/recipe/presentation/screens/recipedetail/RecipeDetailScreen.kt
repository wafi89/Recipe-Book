package asia.fourtitude.recipe.presentation.screens.recipedetail


import android.widget.Toast
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.presentation.screens.recipedetail.views.RecipeDetailContent
import asia.fourtitude.recipe.presentation.screens.recipedetail.views.RecipeDetailTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    isPromptDelete: MutableState<Boolean>,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isOnEditMode = remember { mutableStateOf(false) }
    val recipe = viewModel.selectedRecipe.value

    val msgDeleteSuccessfully = stringResource(id = R.string.recipe_detail_delete_successfully)

    val isDeletingRecipe = remember { mutableStateOf(false) }
    val crumpledFraction by animateFloatAsState(
        targetValue = if (isDeletingRecipe.value) 1f else 0f,
        animationSpec = TweenSpec(durationMillis = 500)
    )

    if (recipe != null) {

        val nameState = remember { mutableStateOf(TextFieldValue(recipe.name)) }
        val descriptionState = remember { mutableStateOf(TextFieldValue(recipe.description)) }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = 1 - crumpledFraction
                        scaleY = 1 + crumpledFraction
                        rotationZ = crumpledFraction * 20
                        alpha = 1 - crumpledFraction
                    }
            ) {
                // Content of the view to be crumpled
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    RecipeDetailTopAppBar(
                        isOnEditMode = isOnEditMode,
                        isPromptDelete = isPromptDelete,
                        recipeName = recipe.name,
                        onNavigateUpClick = {
                            navController.popBackStack()
                        },
                        onDoneClick = {
                            isOnEditMode.value = false
                            recipe.name = nameState.value.text
                            recipe.description = descriptionState.value.text
                            viewModel.updateRecipe(recipe)
                        },
                        onMoreOptionsClick = {},
                        onDeleteClick = {
                            isPromptDelete.value = false
                            viewModel.deleteRecipe(
                                recipe,
                                onDeleted = {
                                    if (it == null) {
                                        isDeletingRecipe.value = true
                                        scope.launch {
                                            delay(500)
                                            Toast.makeText(
                                                context,
                                                msgDeleteSuccessfully,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.popBackStack()
                                        }
                                    }
                                }
                            )
                        },
                    )

                    Divider()

                    RecipeDetailContent(
                        featuredImageUrl = recipe.featuredImageUrl,
                        nameState = nameState,
                        descriptionState = descriptionState,
                        readOnly = isOnEditMode.value.not(),
                        isPromptDelete = isPromptDelete,
                        recipe = recipe,
                        ingredients = recipe.ingredients,
                        steps = recipe.steps,
                    )

                }
            }
        }

    }

}











